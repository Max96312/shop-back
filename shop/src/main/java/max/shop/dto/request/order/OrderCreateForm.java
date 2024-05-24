package max.shop.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateForm {
    @NotNull(message = "구매자 ID는 필수입니다.")
    String memberId;

    OrderItemInfo orderItemInfo;
}
