package com.chocolatestore.domain.DTO;

import com.chocolatestore.domain.Product;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

public class ManufacturerDTO {
    private long id;

    private String name;

    private Timestamp created;

    private Timestamp changed;

    private Collection<Product> products;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDTO that = (ManufacturerDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(created, that.created) && Objects.equals(changed, that.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, changed);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(long id, String name, Timestamp created, Timestamp changed, Collection<Product> products) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.changed = changed;
        this.products = products;
    }
}
