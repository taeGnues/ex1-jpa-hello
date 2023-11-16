package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 상속관계 매핑 X, 엔티티 X, 테이블 매핑 X
// 조회 검색 불가
// 직접 생성해서 쓸일 없어 추상 클래스 권장.
// ex : em.find(BaseEntity.class, member) 등 불가.

@MappedSuperclass // 공통의 매핑정보만 담는 부모클래스. 추후 자동화할 수 있음.
public abstract class BaseEntity { // 추상클래스로 하는게 좋음.
    private String createdBy;
    private LocalDateTime createDate;
    private String lastModiiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getLastModiiedBy() {
        return lastModiiedBy;
    }

    public void setLastModiiedBy(String lastModiiedBy) {
        this.lastModiiedBy = lastModiiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
