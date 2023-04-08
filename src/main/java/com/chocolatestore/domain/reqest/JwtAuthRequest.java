package com.chocolatestore.domain.reqest;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String login;
    private String password;
}