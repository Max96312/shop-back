package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.dto.request.member.MemberRegisterForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="USERS",
        uniqueConstraints = {
            @UniqueConstraint( name="USER_ID_UNIQUE", columnNames={"user_id"}
        )})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id", nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

    private String password;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<AddressEntity> addresses = new ArrayList<>();

    public static Member createUser(MemberRegisterForm form){
        Member user = new Member();
        user.id = form.getId();
        user.name = form.getName();
        user.password = form.getPassword();
        user.phoneNumber = form.getPhoneNumber();
        user.email = form.getEmail();
        return user;
    }

    public void changeName(String memberName) {
        this.name = memberName;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member user = (Member) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
