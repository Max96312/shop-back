package max.shop.service.item;

import max.shop.domain.Item;
import max.shop.domain.type.ItemType;
import max.shop.dto.request.item.ItemCreateForm;
import max.shop.dto.request.item.UpdateItemDto;
import max.shop.dto.response.item.ItemDtoResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    // Item CRUD
    void saveItem(ItemCreateForm item);
    Item findOne(Long id);
    void updateItem(Long itemId, UpdateItemDto item);
    void deleteItem(Long id);

    // Items
    List<Item> findItems();

    // App Service
    List<ItemDtoResponse> getItemPage(ItemType type, Pageable pageable);
    ItemDtoResponse getItem(Long id);
}
