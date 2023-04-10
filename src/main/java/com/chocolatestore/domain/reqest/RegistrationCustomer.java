package com.chocolatestore.domain.reqest;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RegistrationCustomer {

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

    @NotNull
    @Length(max = 200)
    private String login;

    @NotNull
    @Length(max = 200)
    private String password;

    @Override
    public String toString() {
        return "RegistrationCustomer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationCustomer that = (RegistrationCustomer) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, phone, email, login, password);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistrationCustomer() {
    }

    public RegistrationCustomer(String firstName, String lastName, String address, String phone, String email, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
