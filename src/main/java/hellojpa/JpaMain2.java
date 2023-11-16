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
            member.setLastModiiedBy("kim");
            em.persist(member);

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
