package com.chocolatestore.domain.DTO;

import java.util.Objects;

public class OrderDTORequestCreate {

    private Long productId;
    private Long customerId;
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderDTORequest{" +
                "productId=" + productId +
                ", customerId=" + customerId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTORequestCreate that = (OrderDTORequestCreate) o;
        return Objects.equals(productId, that.productId) && Objects.equals(customerId, that.customerId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId, quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderDTORequestCreate() {
    }

    public OrderDTORequestCreate(Long productId, Long customerId, Integer quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
    }
}
