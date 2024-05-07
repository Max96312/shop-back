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
public class OrderDetail extends CreateTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_detail_id")
    private Long id;

    private int productCount;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
