package com.chocolatestore.security;

import com.chocolatestore.domain.reqest.JwtAuthRequest;
import com.chocolatestore.domain.reqest.RegistrationCustomer;
import com.chocolatestore.domain.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class SecurityController {

    SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpResponse> registrationUser(@RequestBody RegistrationCustomer registrationCustomer) {
        return new ResponseEntity<>(securityService.registration(registrationCustomer) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody JwtAuthRequest jwtAuthRequest) {
        String result = securityService.getToken(jwtAuthRequest);
        if (!result.isBlank()) {
            return new ResponseEntity<>(new JwtResponse(result), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}