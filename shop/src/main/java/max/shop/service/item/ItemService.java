package max.shop.service.item;

import max.shop.domain.Item;
import max.shop.dto.request.ItemCreateForm;
import max.shop.dto.request.UpdateItemDto;

import java.util.List;

public interface ItemService {
    // Item CRUD
    public void saveItem(ItemCreateForm item);
    public Item findOne(Long id);
    public void updateItem(Long itemId, UpdateItemDto item);
    public void deleteItem(Long id);

    // Items R
    public List<Item> findItems();
}
