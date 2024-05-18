package max.shop.dto;

import max.shop.dto.request.item.ItemCreateForm;

public record ItemDto(
        Long id,
        String itemName,
        int itemPrice,
        int stockQuantity,
        String itemDescription
) {
    public static ItemDto of(ItemCreateForm form) {
        return new ItemDto(null, form.getName(), form.getPrice(), form.getStockQuantity(), form.getDescription());
    }
}
