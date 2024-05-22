package max.shop.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import max.shop.domain.Item;
import max.shop.domain.type.ItemStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDtoResponse {
    private Long id;
    private String name;
    private int price;
    private ItemStatus status;

    public ItemDtoResponse(Item entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.status = entity.getItemStatus();
    }
}
