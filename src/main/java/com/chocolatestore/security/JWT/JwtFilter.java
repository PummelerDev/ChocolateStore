package com.chocolatestore.security.JWT;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends GenericFilterBean {

    private String token;
    private String customerLogin;
    private final JwtProvider jwtProvider;
    private final CustomerService customerService;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, CustomerService customerService) {
        this.jwtProvider = jwtProvider;
        this.customerService = customerService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        token = ((HttpServletRequest) servletRequest).getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtProvider.isValid(token)) {
                customerLogin = jwtProvider.getLoginFromJwt(token);
                Optional<Customer> customer = customerService.getCustomerByLogin(customerLogin);
                if (customer.isPresent()) {
                    UserDetails userDetails = User
                            .withUsername(customer.get().getLogin())
                            .password(customer.get().getPassword())
                            .roles(customerService.getRole(customer.get().getId()))
                            .build();
                    UsernamePasswordAuthenticationToken userAuth =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(userAuth);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
