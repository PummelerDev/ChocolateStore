package com.chocolatestore.domain.DTO;

import java.util.List;
import java.util.Objects;

public class OrderDTOResponseByNumber {

    private long orderNumber;
    private CustomerDTO customer;
    private List<ProductDTOResponseByNumber> products;
    private double totalPrice;
    private boolean collected;
    private boolean finished;

    @Override
    public String toString() {
        return "OrderDTOResponseByNumber{" +
                "orderNumber=" + orderNumber +
                ", customer=" + customer +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", collected=" + collected +
                ", finished=" + finished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTOResponseByNumber that = (OrderDTOResponseByNumber) o;
        return orderNumber == that.orderNumber && Double.compare(that.totalPrice, totalPrice) == 0 && collected == that.collected && finished == that.finished && Objects.equals(customer, that.customer) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, customer, products, totalPrice, collected, finished);
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ProductDTOResponseByNumber> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTOResponseByNumber> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public OrderDTOResponseByNumber() {
    }

    public OrderDTOResponseByNumber(long orderNumber, CustomerDTO customer, List<ProductDTOResponseByNumber> products, double totalPrice, boolean collected, boolean finished) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.collected = collected;
        this.finished = finished;
    }
}
