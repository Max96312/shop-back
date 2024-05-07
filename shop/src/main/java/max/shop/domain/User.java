package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.domain.type.Gender;

import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, updatable = false, unique = true, length = 20)
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
}
