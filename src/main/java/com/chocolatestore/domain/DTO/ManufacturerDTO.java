package com.chocolatestore.domain.DTO;

import java.util.Collection;
import java.util.Objects;

public class ManufacturerDTO {

    private String name;

    private Collection<ProductDTOResponse> products;

    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDTO that = (ManufacturerDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, products);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ProductDTOResponse> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductDTOResponse> products) {
        this.products = products;
    }

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(String name, Collection<ProductDTOResponse> products) {
        this.name = name;
        this.products = products;
    }
}
