package max.shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import max.shop.domain.type.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchRequest {
    String userName;
    OrderStatus orderStatus;
}
