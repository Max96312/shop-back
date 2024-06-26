package max.shop.service.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import max.shop.common.exception.ItemNotFoundException;
import max.shop.domain.Item;
import max.shop.domain.type.ItemType;
import max.shop.dto.request.item.ItemCreateForm;
import max.shop.dto.request.item.UpdateItemDto;
import max.shop.dto.response.item.ItemDtoResponse;
import max.shop.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public void saveItem(ItemCreateForm form) {
        itemRepository.save(new Item().createItem(form));
    }

    @Override
    @Transactional
    public void updateItem(Long itemId, UpdateItemDto param) {
        Item findItem = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        findItem.updateItem(param);
    }

    @Override
    public Item findOne(Long id) {
        return itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<ItemDtoResponse> getItemPage(ItemType type, Pageable pageable) {
        Page<Item> items = itemRepository.findItemPage(type, pageable);

        return items.stream()
                .map(ItemDtoResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDtoResponse getItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return new ItemDtoResponse(item);
    }
}
