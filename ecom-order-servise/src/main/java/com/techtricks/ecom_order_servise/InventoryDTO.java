package com.techtricks.ecom_order_servise;

public class InventoryDTO {

    private Long productId;

    private Integer quantity;

    public InventoryDTO() {
    }

    public InventoryDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
