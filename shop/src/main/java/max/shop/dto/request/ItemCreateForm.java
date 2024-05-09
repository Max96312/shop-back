package max.shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemCreateForm(
        @NotBlank(message = "상품명은 필수 입력란입니다.")
        @Length(max = 20, message = "상품명의 길이는 최대 20자까지 가능합니다.")
        String itemName,
        @NotBlank(message = "상품 가격은 필수 입력란입니다.")
        @Length(min = 1, max = 1000000, message = "상품 가격은 1~1000000 사이로 설정해주세요.")
        int itemPrice,
        @NotBlank(message = "상품 수량은 필수 입력란입니다.")
        @Length(min = 1, max = 999, message = "상품 수량은 1~999 사이로 설정해주세요.")
        int stockQuantity,
        @NotBlank(message = "상품 설명은 필수 입력란입니다.")
        @Length(max = 1000, message = "상품 설명의 길이는 최대 1000자까지 가능합니다.")
        String itemDescription
) {
    public static ItemCreateForm of(String itemName, int itemPrice, int stockQuantity, String itemDescription) {
        return new ItemCreateForm(itemName, itemPrice, stockQuantity, itemDescription);
    }
}
