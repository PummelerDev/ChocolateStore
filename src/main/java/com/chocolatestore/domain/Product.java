package com.chocolatestore.domain;

import com.chocolatestore.utils.Kind;
import com.chocolatestore.utils.Topping;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "kind")
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column(name = "topping")
    @Enumerated(EnumType.STRING)
    private Topping topping;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private int weight;

    @Column(name = "price")
    private double price;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
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
        Product product = (Product) o;
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

    public Product() {
    }

    public Product(long id, Kind kind, Topping topping, Manufacturer manufacturer, String name, String description, int weight, double price, Timestamp created, Timestamp changed) {
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