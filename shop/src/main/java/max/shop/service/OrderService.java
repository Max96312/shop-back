package max.shop.service;

import max.shop.domain.Order;
import max.shop.dto.request.order.OrderCreateForm;
import max.shop.dto.request.order.OrderListCreateFrom;
import max.shop.dto.response.order.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findOrders();

    // app service
    Long order(OrderCreateForm form);
    Long orderFromItemList(OrderListCreateFrom form);

    void cancelOrder(Long orderId);

    OrderResponseDto getOrder();
}
