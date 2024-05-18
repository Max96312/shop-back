package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.dto.request.member.Address;

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
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public AddressEntity addAddress(Address address, Member member) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.address = address;
        addressEntity.member = member;
        return addressEntity;
    }
}
