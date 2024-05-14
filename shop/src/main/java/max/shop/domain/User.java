package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import max.shop.domain.embed.Address;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.domain.type.Gender;
import max.shop.dto.request.UserRegisterForm;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="USERS",
        uniqueConstraints = {
            @UniqueConstraint( name="USER_ID_UNIQUE", columnNames={"user_id"}
        )})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false, length = 20)
    private String id;

    @Column(length = 20, nullable = false)
    private String username;

    private String password;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    private LocalDateTime birthday;

    private boolean isAdmin = false;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    @Setter
    private Address address;

    public static User createUser(UserRegisterForm form){
        User user = new User();
        user.id = form.getUserId();
        user.username = form.getUsername();
        user.password = form.getPassword();
        user.phoneNumber = form.getPhoneNumber();
        user.birthday = form.getBirthday();
        user.gender = form.getGender();
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
