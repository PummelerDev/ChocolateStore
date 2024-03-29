package com.chocolatestore.domain.request;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class JwtAuthRequest {

    @NotNull
    private String login;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return "JwtAuthRequest{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtAuthRequest that = (JwtAuthRequest) o;
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

    public JwtAuthRequest() {
    }

    public JwtAuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}