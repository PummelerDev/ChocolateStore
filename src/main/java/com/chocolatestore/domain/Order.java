package com.chocolatestore.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "orders_id_seq1", allocationSize = 1)
    private long id;

    @Column(name = "order_number")
    private long orderNumber;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column(name = "cancelled")
    private boolean cancelled;

    @Column(name = "collected")
    private boolean collected;

    @Column(name = "finished")
    private boolean finished;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
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
        Order order = (Order) o;
        return id == order.id && orderNumber == order.orderNumber && quantity == order.quantity && cancelled == order.cancelled && collected == order.collected && finished == order.finished && Objects.equals(product, order.product) && Objects.equals(customer, order.customer) && Objects.equals(created, order.created) && Objects.equals(changed, order.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber, product, customer, quantity, created, changed, cancelled, collected, finished);
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

    public Order() {
    }

    public Order(long id, long orderNumber, Product product, Customer customer, int quantity, Timestamp created, Timestamp changed, boolean cancelled, boolean collected, boolean finished) {
        this.id = id;
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