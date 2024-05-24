package max.shop.dto.request.order;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemInfo {
    @NotNull(message = "상품 ID는 필수입니다.")
    private Long itemId;

    @NotNull(message = "상품 개수는 필수입니다.")
    @Min(value = 1, message = "상품의 최소 주문 개수는 1개입니다.")
    @Max(value = 10, message = "상품의 최대 주문 수는 10개입니다.")
    private Integer count;
}
