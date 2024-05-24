package max.shop.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListCreateFrom {
    @NotNull(message = "구매자 ID는 필수입니다.")
    private String memberId;

    @NotNull(message = "상품 정보는 필수입니다.")
    private List<OrderItemInfo> orderItemInfoList;
}
