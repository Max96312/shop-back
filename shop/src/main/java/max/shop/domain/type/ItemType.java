package max.shop.domain.type;

import lombok.Getter;

public enum ItemType {
    OUTER("아우터"),
    COAT("코트"),
    TOP("상의"),
    PANTS("하의"),
    BAG("가방"),
    ACC("악세사리");

    @Getter
    private final String description;

    ItemType(String description) {
        this.description = description;
    }
}
