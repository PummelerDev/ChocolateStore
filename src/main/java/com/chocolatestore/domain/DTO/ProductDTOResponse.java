package com.chocolatestore.domain.DTO;

import com.chocolatestore.utils.Kind;
import com.chocolatestore.utils.Topping;

import java.util.Objects;

public class ProductDTOResponse {

    private Kind kind;

    private Topping topping;

    private String manufacturerName;

    private String name;

    private String description;

    private int weight;

    private double price;

    @Override
    public String toString() {
        return "ProductDTOResponse{" +
                "kind=" + kind +
                ", topping=" + topping +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTOResponse that = (ProductDTOResponse) o;
        return weight == that.weight && Double.compare(that.price, price) == 0 && kind == that.kind && topping == that.topping && Objects.equals(manufacturerName, that.manufacturerName) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, topping, manufacturerName, name, description, weight, price);
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

    public ProductDTOResponse() {
    }

    public ProductDTOResponse(Kind kind, Topping topping, String manufacturerName, String name, String description, int weight, double price) {
        this.kind = kind;
        this.topping = topping;
        this.manufacturerName = manufacturerName;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
    }
}