package max.shop.service;

import lombok.RequiredArgsConstructor;
//import max.shop.dto.request.CreateProductRequest;
import max.shop.common.exception.ItemNotFoundException;
import max.shop.domain.Item;
import max.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }
}
