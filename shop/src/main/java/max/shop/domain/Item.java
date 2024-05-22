package max.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.common.exception.NotEnoughStockException;
import max.shop.domain.time.BaseTimeEntity;
import max.shop.domain.type.ItemStatus;
import max.shop.domain.type.ItemType;
import max.shop.dto.request.item.ItemCreateForm;
import max.shop.dto.request.item.UpdateItemDto;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(length = 20)
    private String name;

    private int price;
    private int stockQuantity;

    @Column(length = 1000)
    private String description;
    //TODO: private String productImageUrl;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    //생성 메소드
    public Item createItem(ItemCreateForm form) {
        Item item = new Item();
        item.name = form.getName();
        item.price = form.getPrice();
        item.stockQuantity = form.getStockQuantity();
        item.description = form.getDescription();
        item.itemStatus = ItemStatus.SALE;
        item.type = form.getItemType();
        return item;
    }

    public void updateItem(UpdateItemDto form) {
        this.name = form.getName();
        this.price = form.getPrice();
        this.stockQuantity = form.getStockQuantity();
        this.description = form.getDescription();
        this.itemStatus = ItemStatus.SALE;
    }

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
