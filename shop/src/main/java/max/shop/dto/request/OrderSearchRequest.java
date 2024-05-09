package max.shop.dto.request;

import max.shop.domain.type.OrderStatus;

public record OrderSearchRequest(
            String userName,
            OrderStatus orderStatus
) {
}
