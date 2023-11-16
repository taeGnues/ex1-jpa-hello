package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @Column(name = "LOCKER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker") // 양방향 일대일
    private Member member;

}
