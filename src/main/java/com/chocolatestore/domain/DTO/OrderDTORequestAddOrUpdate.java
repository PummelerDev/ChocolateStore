package com.chocolatestore.domain.DTO;

import java.util.Objects;

public class OrderDTORequestAddOrUpdate {

    private Long productId;
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderDTORequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTORequestAddOrUpdate that = (OrderDTORequestAddOrUpdate) o;
        return Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
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

    public OrderDTORequestAddOrUpdate() {
    }

    public OrderDTORequestAddOrUpdate(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
