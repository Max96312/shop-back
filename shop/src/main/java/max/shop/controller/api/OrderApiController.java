package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.dto.request.order.OrderCreateForm;
import max.shop.dto.request.order.OrderListCreateFrom;
import max.shop.dto.response.SingleResult;
import max.shop.serviceImp.OrderServiceImpl;
import max.shop.serviceImp.ResponseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderApiController {

    private final OrderServiceImpl orderService;
    private final ResponseService responseService;


    /**
     * Item 주문
     */
    @PostMapping
    public SingleResult<Long> order(@RequestBody OrderCreateForm form){
        return responseService.handleSingleResult(orderService.order(form));
    }

    /**
     * Item List 주문
     */
    @PostMapping("/list")
    public SingleResult<Long> orders(@RequestBody OrderListCreateFrom form) {
        return responseService.handleSingleResult(orderService.orderFromItemList(form));
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
