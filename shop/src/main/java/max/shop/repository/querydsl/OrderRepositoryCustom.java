package max.shop.repository.querydsl;

import max.shop.domain.Order;
import max.shop.dto.request.OrderSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrders(OrderSearchRequest searchRequest, Pageable pageable);
}
