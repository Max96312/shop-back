package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.time.CreateTimeEntity;
import max.shop.domain.type.DeliveryStatus;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetails extends CreateTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_detail_id")
    private Long id;

    private int productCount;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
