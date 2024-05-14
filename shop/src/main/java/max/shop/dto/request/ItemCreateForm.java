package max.shop.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateForm {
    @NotBlank(message = "상품명은 필수 입력란입니다.")
    @Length(max = 20, message = "상품명의 길이는 최대 20자까지 가능합니다.")
    String name;

    @NotNull(message = "상품 가격은 필수 입력란입니다.")
    @Min(1) @Max(1000000)
    int price;

    @NotNull(message = "상품 수량은 필수 입력란입니다.")
    @Min(1) @Max(999)
    int stockQuantity;

    @NotBlank(message = "상품 설명은 필수 입력란입니다.")
    @Length(max = 1000, message = "상품 설명의 길이는 최대 1000자까지 가능합니다.")
    String description;

//    public static ItemCreateForm of(String name, int price, int stockQuantity, String description) {
//        return new ItemCreateForm(name, price, stockQuantity, description);
//    }

//    public Item toEntity(ItemCreateForm form) {
//        return Item.createItem(form);
//    }
}
