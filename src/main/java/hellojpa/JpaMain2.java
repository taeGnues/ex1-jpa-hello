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
            // 플러시 모드 옵션도 존재,,
            // 영속성 컨텍스트를 비우지 않음!! 영속성 컨텍스트의 변경 내용을 DB에 동기화. 트랜잭션이라는 작업단위가 중요함.->커밋직전에만 동기화!!

//            Member member = new Member(200L, "member200");
//            em.persist(member); // 영속성 컨텍스트에 추가
//            em.flush();
            // 이 시점에 데이터베이스에 즉시 반영됨 단, flush 하더라도 1차캐시에 있는값들은 유지.
            // 쓰기 지연 sql에 저장소에 있는것들만 반영됨.

// 영속 상태는 1차캐시에 올라온 상태. (em.find 등)
// 준영속 상태는 엔티티가 영속성 상태에서 분리(detached)된 상태.
            // em.detach(member); member(특정 엔티티)를 jpa에서 전혀 관리하지 않음.
            // em.clear(); 영속성 컨텍스트를 초기화함.
// ====================== 객체 지향 스럽지 않음 ===================
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeamId(team.getId());
//
//
//            em.persist(member);
//
//            Member member1 = em.find(Member.class, member.getId());
//            Long findTeamId = member.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);
// ============================================================

            Team team = new Team();
            team.setName("TeamD");
            em.persist(team);

            Member member = new Member();
            member.setName("member3");
            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member); // 객체 상태에서 저장.
//
//            em.flush();
//            em.clear();
            Team findTeam = em.find(Team.class, team.getId()); // 1차캐시에서 가져옴.
            List<Member> memberList = findTeam.getMembers();

            System.out.println("=============");
            System.out.println(findTeam); // 무한루프 주의,, JSOn 생성 라이브러리 주의
            // 롬복에서는 toString은 빼라.
//            for (Member m : findTeam.getMembers()) { // 1차 캐시에서 가져온 값은 순수 객체 상태. 즉 member들이 없음.
//                System.out.println(m.getName());
//            }
            // 따라서 team.getMembers().add(member); 을 추가하여 양방향에 추가해야함.
            // 즉 member.setTeam() / team.getMembers().add(member) 모두 진행해야
            System.out.println("=============");


            // 양방향 관계시에는 양쪽에서 세팅을 해줘야하낟.
            // 양방향 관계에서 무한 루프 주의해야함!!
//
//            Team newTeam = em.find(Team.class, 100L);
//            newTeam.setName("hello");
//            em.persist(newTeam);

            // id IDENTITY 전략에서는 commit 시점이 아닌 이 시점에서 바로 insert query를 날림.
            // db insert한 시점에서 바로 pk값으로 사용함. 버퍼에 모아서 insert하는것이 불가함,,

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
