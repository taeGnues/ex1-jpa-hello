//package hellojpa;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx =em.getTransaction();
//
//        tx.begin();
//
//        try {
//            // 비영속
//
////            Member member = new Member();
////            member.setId(101L);
////            member.setName("HelloJPA");
////
////            // 영속
////            em.persist(member);
//
//            Member member1 = em.find(Member.class, 150L);
//            System.out.println(member1.getName());
//            member1.setName("ZZZZZP"); // jpa는 값이 바뀌면 커밋되는 시점에 업데이트쿼리도 진행됨
//
//
//            System.out.println("==================");
//            tx.commit();
//        }catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        emf.close();
//
//    }
//}
