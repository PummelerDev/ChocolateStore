package com.chocolatestore.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturer_seq")
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturers_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manufacturer")
    @JsonBackReference
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
        Manufacturer that = (Manufacturer) o;
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

    public Manufacturer() {
    }

    public Manufacturer(long id, String name, Timestamp created, Timestamp changed, Collection<Product> products) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.changed = changed;
        this.products = products;
    }
}
