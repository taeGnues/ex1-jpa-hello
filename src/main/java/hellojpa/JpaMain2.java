package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx =em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setName("hello");
            em.persist(member);

            em.flush();
            em.clear(); // 1차캐시 비우기.

//            Member findMember = em.find(Member.class, member.getId()); -> 쿼리나감
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName());

            Member findMemberRef = em.getReference(Member.class, member.getId()); // 이거만 하면 쿼리 안나감
            System.out.println(findMemberRef.getClass()); // 데이터 베이스 조회를 미루는 가짜 객체. 프록시 초기화.
            System.out.println(findMemberRef.getId()); // 데이터 참조시 쿼리가 나감. (이때 실제 클래스를 접근)
            System.out.println(findMemberRef.getName());
            System.out.println(findMemberRef.getClass()); // 프록시 객체가 원본 객체로 바뀌지 않음!!!
            // 원본 엔티티를 상속받음. == 비교 실패. instance of 사용하기
            // 즉, 프록시 비교할지 실제 객체 클래스 비교할지 알 수 없으므로
            // m1.getClass == m2.getClass를 하면안됨!!!!!!!!!!! => m1 instanceof Member로 해주
            Member member2 = new Member();
            member.setName("hello2");
            em.persist(member2);

            em.flush();
            em.clear();
//
//            Member m2 = em.find(Member.class, member2.getId());
//            m2.getClass();
//            Member ref = em.getReference(Member.class, member2.getId());
//            ref.getClass(); // 프록시 아님 실제 member 객체임!! (영속성 콘텍스트에 있는데 굳이 프록시 객체 생성하는게 손해이기떄문)

            Member ref = em.getReference(Member.class, member2.getId());
            ref.getClass();
//            Member m2 = em.find(Member.class, member2.getId());
//            m2.getClass();
            // 이렇게 순서를 바꿔버리면 m2는 proxy를 반환하게 됨!!

            System.out.println(ref.getClass());
            ref.getName(); // 강제호출.
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(ref)); // 인스턴스 초기화된 경우 true/아니면 false
            // 지금의 경우는 true를 반환

            // 프록시의 초기화 요청은 영속성 컨텍스트에서 일어남. 따라서 만약
//            em.detach(ref);
//            em.close();
            // 과 같이 준영속 상태로 바꾸면 proxy를 초기화할 수 없다고 이야기함
            // 이떄  org.hibernate.LazyInitializationException 예외발생 (jpql 사용시 자주만나는에러)


            System.out.println("==================");
            tx.commit(); // 트랜잭션 커밋 시 플러시가 자동으로 호출됨.
            // em.createQeury() 같이 JPQL 쿼리를 실행 시 플러시를 자동호출한다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }

    private static void printMember(Member member){
        System.out.println("member = " + member.getName());
    }
}
