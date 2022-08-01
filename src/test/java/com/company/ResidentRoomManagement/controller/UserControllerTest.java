package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.User;
import com.company.ResidentRoomManagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User savedUser;

    @BeforeEach
    void setUp() {
        savedUser = User.builder().email("testUser@gmail.com").build();
        savedUser.setId(1L);
    }

    @Test
    void registerNewUser() throws Exception {
        User inputUser = User.builder().email("testUser@gmail.com").build();
        Mockito.when(userService.registerNewUser(inputUser)).thenReturn(savedUser);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"createdAt\": \"2022-08-01T03:14:21.690Z\",\n" +
                        "  \"updatedAt\": \"2022-08-01T03:14:21.690Z\",\n" +
                        "  \"username\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"phoneNo\": \"string\",\n" +
                        "  \"email\": \"testUser@gmail.com\",\n" +
                        "  \"passportNo\": \"string\",\n" +
                        "  \"gender\": \"string\",\n" +
                        "  \"roles\": \"string\",\n" +
                        "  \"permissions\": \"string\",\n" +
                        "  \"active\": 0,\n" +
                        "  \"userGroupId\": 0\n" +
                        "}")).andExpect(status().isCreated());
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(userService.findUserById(1L)).thenReturn(savedUser);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.email").value(savedUser.getEmail()));
    }
}