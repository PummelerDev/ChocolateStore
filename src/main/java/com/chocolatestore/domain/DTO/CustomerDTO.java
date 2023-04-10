package com.chocolatestore.domain.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class CustomerDTO {
    @NotNull
    @Length(max = 50)
    private String firstName;

    @NotNull
    @Length(max = 50)
    private String lastName;

    @NotNull
    @Length(max = 250)
    private String address;

    @NotNull
    @Length(max = 50)
    private String phone;

    @NotNull
    @Length(max = 250)
    @Email(regexp = "^([a-zA-Z0-9]+(\\.|\\-|_)?[a-zA-Z0-9]+)+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,4}$")
    private String email;

    @Positive
    private double purchaseAmount;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", purchaseAmount=" + purchaseAmount +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Double.compare(that.purchaseAmount, purchaseAmount) == 0 && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, phone, purchaseAmount, email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName, String lastName, String address, String phone, String email, double purchaseAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.purchaseAmount = purchaseAmount;
    }
}