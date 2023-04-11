package com.chocolatestore.controller;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTO;
import com.chocolatestore.domain.DTO.CustomerDTOForUpdate;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
import com.chocolatestore.security.JWT.JwtProvider;
import com.chocolatestore.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private JwtProvider jwtProvider;

    private final MockMvc mockMvc;
    private Customer customer;
    private CustomerDTO customerDTO;
    private CustomerDTOForUpdate customerDTOForUpdate;
    private List<Customer> customers;
    private MvcResult mvcResult;

    @Autowired
    CustomerControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("testFname");
        customer.setLastName("testLname");
        customer.setAddress("address");
        customer.setPhone("phone");
        customer.setEmail("email@google.com");
        customer.setPurchaseAmount(10);
        customer.setLogin("login");
        customer.setPassword("password");
        customer.setCreated(new Timestamp(new Date().getTime()));
        customer.setChanged(new Timestamp(new Date().getTime()));
        customer.setDeleted(false);

        customerDTO = new CustomerDTO();
        customerDTO.setFirstName("testFname");
        customerDTO.setLastName("testLname");
        customerDTO.setAddress("address");
        customerDTO.setPhone("phone");
        customerDTO.setEmail("email@google.com");
        customerDTO.setPurchaseAmount(10);

        customerDTOForUpdate = new CustomerDTOForUpdate();
        customerDTOForUpdate.setFirstName("testFname");
        customerDTOForUpdate.setLastName("testLname");
        customerDTOForUpdate.setAddress("address");
        customerDTOForUpdate.setPhone("phone");
        customerDTOForUpdate.setEmail("email@google.com");

        customers = new ArrayList<>();
        customers.add(customer);

        mvcResult = null;
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void getAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn((ArrayList<Customer>) customers);
        mvcResult = mockMvc
                .perform(get("/customer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(customerService, Mockito.times(1)).getAllCustomers();
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void getCustomerById() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);
        mvcResult = mockMvc
                .perform(get("/customer/get/{id}", anyLong()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(customerService, Mockito.times(1)).getCustomerById(anyLong());
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void getCurrentCustomerByLogin() throws Exception {
        String token = "token";
        when(jwtProvider.getLoginFromJwt(anyString())).thenReturn(token);
        when(customerService.getCustomerDTOByLogin(token)).thenReturn(customerDTO);
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/customer/current", anyString())
                        .header("authorization", "Bearer afajpfjdsap")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(customerService, Mockito.times(1)).getCustomerDTOByLogin(anyString());
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void updateCustomerByLogin() throws Exception {
        String token = "token";
        when(jwtProvider.getLoginFromJwt(anyString())).thenReturn(token);
        when(customerService.updateByLogin(token, customerDTOForUpdate)).thenReturn(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter wrapper = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = wrapper.writeValueAsString(customerDTOForUpdate);

        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/customer/update", anyString())
                        .header("authorization", "Bearer afajpfjdsap")
                        .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isNoContent())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(customerService, Mockito.times(1)).updateByLogin(token, customerDTOForUpdate);
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void deleteCustomerByLogin() throws Exception {
        String token = "token";
        when(jwtProvider.getLoginFromJwt(anyString())).thenReturn(token);
        when(customerService.deleteCustomerByLogin(token)).thenReturn(true);
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/customer/current/delete", anyString())
                        .header("authorization", "Bearer afajpfjdsap")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(customerService, Mockito.times(1)).deleteCustomerByLogin(token);
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void restoreCustomerById() throws Exception {
        when(customerService.restoreCustomerById(anyLong())).thenReturn(true);
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/customer/{id}/restore", anyLong()))
                .andExpect(status().isNoContent())
                .andReturn();
        Mockito.verify(customerService, Mockito.times(1)).restoreCustomerById(anyLong());
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void removeCustomerById() throws Exception {
        when(customerService.removeCustomerById(anyLong())).thenReturn(true);
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.delete("/customer/{id}/remove", anyLong()))
                .andExpect(status().isNoContent())
                .andReturn();
        Mockito.verify(customerService, Mockito.times(1)).removeCustomerById(anyLong());
    }

    @Test
    @WithMockUser(username = "login", roles = "ADMIN")
    void getLoginAndPassword() throws Exception {
        String token = "token";
        CustomerDTOLoginPassword cdlp = new CustomerDTOLoginPassword("login", "password");
        when(jwtProvider.getLoginFromJwt(anyString())).thenReturn(token);
        when(customerService.getLoginAndPassword(token)).thenReturn(cdlp);
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/customer/login", anyString())
                        .header("authorization", "Bearer afajpfjdsap")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), allOf(notNullValue()));
        Mockito.verify(customerService, Mockito.times(1)).getLoginAndPassword(token);
    }
}