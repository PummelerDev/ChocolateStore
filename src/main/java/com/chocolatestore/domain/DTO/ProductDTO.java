package com.chocolatestore.domain.DTO;

import com.chocolatestore.domain.Kind;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.domain.Topping;

import java.sql.Timestamp;
import java.util.Objects;

public class ProductDTO {

    private long id;

    private Kind kind;

    private Topping topping;

    private Manufacturer manufacturer;

    private String name;

    private String description;

    private int weight;

    private double price;

    private Timestamp created;

    private Timestamp changed;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", kind=" + kind +
                ", topping=" + topping +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", created=" + created +
                ", changed=" + changed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO product = (ProductDTO) o;
        return id == product.id && weight == product.weight && Double.compare(product.price, price) == 0 && kind == product.kind && topping == product.topping && Objects.equals(manufacturer, product.manufacturer) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(created, product.created) && Objects.equals(changed, product.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kind, topping, manufacturer, name, description, weight, price, created, changed);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

    public ProductDTO() {
    }

    public ProductDTO(long id, Kind kind, Topping topping, Manufacturer manufacturer, String name, String description, int weight, double price, Timestamp created, Timestamp changed) {
        this.id = id;
        this.kind = kind;
        this.topping = topping;
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.created = created;
        this.changed = changed;
    }
}