package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.common.exception.NotEnoughStockException;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.domain.type.ItemStatus;
//import max.shop.dto.request.CreateProductRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(length = 20)
    private String name;

    private int price;
    private int stockQuantity;
    private String description;
    //TODO: private String productImageUrl;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;


    //생성 메소드
//    public static Item createProduct(CreateProductRequest request) {
//        return new Item(request.productName(), request.productPrice(), request.productStock(), request.productDescription(),);
//    }

    //비즈니스 로직

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("현재 수량이 부족합니다.");
        }
        this.stockQuantity = restStock;
    }

    // 상품 상태 변경
    public void changeStatusSale(){
        this.itemStatus = ItemStatus.SALE;
    }

    public void changeStatusHide(){
        this.itemStatus = ItemStatus.HIDE;
    }

    public void changeStatusSoldOut(){
        this.itemStatus = ItemStatus.SOLDOUT;
    }

    public void changeStatus(){
        this.itemStatus = ItemStatus.REMOVE;
    }
}
