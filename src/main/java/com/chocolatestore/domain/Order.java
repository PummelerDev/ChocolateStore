package com.chocolatestore.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class Order {
    private long id;
    private long orderNumber;
    private long productId;
    private long customerId;
    private int quantity;
    private Timestamp created;
    private Timestamp changed;
    private boolean cancelled;
    private boolean finished;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
                ", productId=" + productId +
                ", customerId=" + customerId +
                ", quantity=" + quantity +
                ", created=" + created +
                ", changed=" + changed +
                ", cancelled=" + cancelled +
                ", finished=" + finished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && orderNumber == order.orderNumber && productId == order.productId && customerId == order.customerId && quantity == order.quantity && cancelled == order.cancelled && finished == order.finished && Objects.equals(created, order.created) && Objects.equals(changed, order.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber, productId, customerId, quantity, created, changed, cancelled, finished);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
