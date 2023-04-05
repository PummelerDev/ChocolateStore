package com.chocolatestore.domain.DTO;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

public class OrderDTOResponse {

    private long orderNumber;
    private ProductDTOResponse product;
    private CustomerDTO customer;
    private int quantity;
    private Timestamp created;
    private Timestamp changed;
    private boolean cancelled;
    private boolean collected;
    private boolean finished;

    @Override
    public String toString() {
        return "OrderDTOResponse{" +
                "orderNumber=" + orderNumber +
                ", product=" + product +
                ", customer=" + customer +
                ", quantity=" + quantity +
                ", created=" + created +
                ", changed=" + changed +
                ", cancelled=" + cancelled +
                ", collected=" + collected +
                ", finished=" + finished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTOResponse that = (OrderDTOResponse) o;
        return orderNumber == that.orderNumber && quantity == that.quantity && cancelled == that.cancelled && collected == that.collected && finished == that.finished && Objects.equals(product, that.product) && Objects.equals(customer, that.customer) && Objects.equals(created, that.created) && Objects.equals(changed, that.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, product, customer, quantity, created, changed, cancelled, collected, finished);
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ProductDTOResponse getProduct() {
        return product;
    }

    public void setProduct(ProductDTOResponse product) {
        this.product = product;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public OrderDTOResponse() {
    }

    public OrderDTOResponse(long orderNumber, ProductDTOResponse product, CustomerDTO customer, int quantity, Timestamp created, Timestamp changed, boolean cancelled, boolean collected, boolean finished) {
        this.orderNumber = orderNumber;
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
        this.created = created;
        this.changed = changed;
        this.cancelled = cancelled;
        this.collected = collected;
        this.finished = finished;
    }
}
