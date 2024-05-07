package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.embed.Address;
import max.shop.domain.time.BaseTimeEntity;

@Entity
@Table(name = "ADDRESSES")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    private Address address;
}
