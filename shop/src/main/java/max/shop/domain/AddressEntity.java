package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.dto.request.user.Address;
import max.shop.dto.request.user.UserRegisterForm;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public AddressEntity createAddress(UserRegisterForm form) {
        AddressEntity address = new AddressEntity();
//        address.user = ;
        address.address = form.getAddress();
        return address;
    }
}
