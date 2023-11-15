package hellojpa;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Member {
    @Id
    private Long id;
    @Column(name = "name") // updatable=false:등록하고 절대 변하지 않는다는 의미, nullable=false:notnull, unique=true
    private String username;

    @Column(precision = 0)
    private BigDecimal money; // BigInteger도 사용가능.

    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) // 날짜/시간/타임스탬프로 구분됨.
    private Date createdDate; // LocalDate, LocalDateTime 사용 시 생략 가능.
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;
    // Local 사용시 @Temporal 생략 가능.
    @Lob
    private String description;
    @Transient // 메모리에서만 사용하겠다. 매핑을 안함!
    private int tmp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }
}