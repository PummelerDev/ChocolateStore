package com.chocolatestore.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CustomerDTOLoginPassword {

    @NotNull
    @Length(max = 200)
    private String login;

    @NotNull
    @Length(max = 200)
    @JsonIgnore
    private String password;

    @Override
    public String toString() {
        return "CustomerDTOLoginPassword{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTOLoginPassword that = (CustomerDTOLoginPassword) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
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

    public CustomerDTOLoginPassword() {
    }

    public CustomerDTOLoginPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
