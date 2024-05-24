package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.domain.type.ItemType;
import max.shop.dto.response.MultipleResult;
import max.shop.dto.response.SingleResult;
import max.shop.dto.response.item.ItemDtoResponse;
import max.shop.service.ItemService;
import max.shop.serviceImp.ResponseService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemApiController {

    private final ItemService itemService;
    private final ResponseService responseService;

    @GetMapping
    public MultipleResult<ItemDtoResponse> searchResumes(@RequestParam ItemType type,
                                                         @PageableDefault(size = 6, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable){
        return responseService.handleListResult(itemService.getItemPage(type, pageable));
    }

    @GetMapping("/{itemId}")
    public SingleResult<ItemDtoResponse> searchResume(@PathVariable Long itemId){
        return responseService.handleSingleResult(itemService.getItem(itemId));
    }
}
