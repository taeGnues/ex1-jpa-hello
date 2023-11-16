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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("hello");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear(); // 1차캐시 비우기.

            Member m = em.getReference(Member.class, member.getId());

            System.out.println(m.getTeam().getClass()); // fetch lazy할 경우, 프록시로 조회됨.
            // team과 member를 자주 사용할 경우, 프록시 사용 대신 fetch Eager를 통해 즉시 조회할 수 있음. (가급적 지연조회 권장)
            System.out.println("==================");
            // team은 실제 사용될때 team 객체로.


            tx.commit(); // 트랜잭션 커밋 시 플러시가 자동으로 호출됨.
            // em.createQeury() 같이 JPQL 쿼리를 실행 시 플러시를 자동호출한다.
        }catch (Exception e){
            e.printStackTrace();
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
