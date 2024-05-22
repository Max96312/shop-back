package max.shop.controller.adminController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import max.shop.domain.Item;
import max.shop.domain.type.ItemType;
import max.shop.dto.request.item.ItemCreateForm;
import max.shop.dto.request.item.UpdateItemDto;
import max.shop.service.item.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * 상품 등록
 * 상품 수정
 * 상품 삭제
 * 상품 조회
 * 상품 리스트 조회
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemServiceImpl itemService;

    /**
     * enum
     */
    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @GetMapping("/items/new")
    public String createItemForm(Model model) {
        model.addAttribute("form", new ItemCreateForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@Validated ItemCreateForm form) {
        itemService.saveItem(form);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        // TODO: 캐스팅을 바로 하는것은 좋지 않다.
        Item item = itemService.findOne(itemId);

        // ModelMapper 등 라이브러리
        UpdateItemDto form = new UpdateItemDto();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setDescription(item.getDescription());
        log.info("get itemId: " + itemId + " form: " + form);
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    //TODO: ITEMID에 대한 권한 검증 로직이 서비스 앞단 혹은 뒷단에 추가
    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, UpdateItemDto form) {
        log.info("post itemId: " + itemId + " form: " + form);
        itemService.updateItem(itemId, form);
        return "redirect:/items";
    }
}
