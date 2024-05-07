package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.domain.type.ProductStatus;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(length = 20)
    private String productName;

    private int productPrice;
    private int productStock;
    private String productDescription;
//    private String productImageUrl;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
}
