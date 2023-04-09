package com.chocolatestore.domain.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class OrderDTORequestCreate {

    @Positive
    private Long productId;

    @Positive
    private Long customerId;

   @Positive
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
