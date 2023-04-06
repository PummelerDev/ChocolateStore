package com.chocolatestore.domain.DTO;

import com.chocolatestore.domain.Kind;
import com.chocolatestore.domain.Topping;

import java.util.Objects;

public class ProductDTOResponseByNumber {

    private long key;
    private Kind kind;
    private Topping topping;
    private String manufacturerName;
    private String name;
    private String description;
    private int weight;
    private double price;
    private int quantity;

    @Override
    public String toString() {
        return "ProductDTOResponseByNumber{" +
                "key=" + key +
                ", kind=" + kind +
                ", topping=" + topping +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTOResponseByNumber that = (ProductDTOResponseByNumber) o;
        return key == that.key && weight == that.weight && Double.compare(that.price, price) == 0 && quantity == that.quantity && kind == that.kind && topping == that.topping && Objects.equals(manufacturerName, that.manufacturerName) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, kind, topping, manufacturerName, name, description, weight, price, quantity);
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDTOResponseByNumber() {
    }

    public ProductDTOResponseByNumber(long key, Kind kind, Topping topping, String manufacturerName, String name, String description, int weight, double price, int quantity) {
        this.key = key;
        this.kind = kind;
        this.topping = topping;
        this.manufacturerName = manufacturerName;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
    }
}