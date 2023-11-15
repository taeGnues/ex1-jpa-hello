package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx =em.getTransaction();

        tx.begin();

        try {
            // 플러시 모드 옵션도 존재,,
            // 영속성 컨텍스트를 비우지 않음!! 영속성 컨텍스트의 변경 내용을 DB에 동기화. 트랜잭션이라는 작업단위가 중요함.->커밋직전에만 동기화!!

            Member member = new Member(200L, "member200");
            em.persist(member); // 영속성 컨텍스트에 추가
            em.flush();
            // 이 시점에 데이터베이스에 즉시 반영됨 단, flush 하더라도 1차캐시에 있는값들은 유지.
            // 쓰기 지연 sql에 저장소에 있는것들만 반영됨.

// 영속 상태는 1차캐시에 올라온 상태. (em.find 등)
// 준영속 상태는 엔티티가 영속성 상태에서 분리(detached)된 상태.
            // em.detach(member); member(특정 엔티티)를 jpa에서 전혀 관리하지 않음.
            // em.clear(); 영속성 컨텍스트를 초기화함.

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
}
