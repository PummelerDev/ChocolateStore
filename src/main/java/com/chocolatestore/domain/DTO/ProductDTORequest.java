package com.chocolatestore.domain.DTO;

import com.chocolatestore.utils.Kind;
import com.chocolatestore.utils.Topping;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class ProductDTORequest {

    @NotNull
    private Kind kind;

    @NotNull
    private Topping topping;

    @Positive
    private long manufacturerId;

    @NotNull
    @Length(max = 50)
    private String name;

    @NotNull
    @Length(max = 500)
    private String description;

    @Positive
    private int weight;

    @Positive
    private double price;

    @Override
    public String toString() {
        return "ProductDTORequest{" +
                "kind=" + kind +
                ", topping=" + topping +
                ", manufacturerId=" + manufacturerId +
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
        ProductDTORequest that = (ProductDTORequest) o;
        return manufacturerId == that.manufacturerId && weight == that.weight && Double.compare(that.price, price) == 0 && kind == that.kind && topping == that.topping && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, topping, manufacturerId, name, description, weight, price);
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

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public ProductDTORequest() {
    }

    public ProductDTORequest(Kind kind, Topping topping, long manufacturerId, String name, String description, int weight, double price) {
        this.kind = kind;
        this.topping = topping;
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
    }
}
