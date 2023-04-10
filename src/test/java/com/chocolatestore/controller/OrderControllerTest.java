package com.chocolatestore.controller;

import com.chocolatestore.domain.Customer;

import com.chocolatestore.domain.Order;
import com.chocolatestore.domain.Product;
import com.chocolatestore.security.JWT.JwtProvider;
import com.chocolatestore.service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private JwtProvider jwtProvider;
    private Order order;
    private MvcResult mvcResult;

    @Autowired
    OrderControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setId(1l);
        order.setOrderNumber(123123l);
        order.setProduct(new Product());
        order.setCustomer(new Customer());
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void getAllOrdersForAdmin() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.getAllOrders()).thenReturn((ArrayList<Order>) orders);
        mvcResult = mockMvc
                .perform(get("/order/all/admin"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(orderService, Mockito.times(1)).getAllOrders();
    }
}