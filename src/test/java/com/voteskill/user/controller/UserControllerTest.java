package com.voteskill.user.controller;

import com.voteskill.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

//http://localhost:8080/users
@WebMvcTest(UserController.class)
public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    UserService userService;
//
//
//    @Test
//    @DisplayName("회원가입 테스트")
//    void createUser() {
//
//    }
//
//    @Test
//    @DisplayName("")
//    void updateUser() {
//        //given
//
//    }
//
//    @Test
//    @DisplayName("")
//    void getUsers() {
//        //given
//
//    }
//
//    @Test
//    @DisplayName("")
//    void getUser() {
//        //given
//
//
//    }
//
//    @Test
//    @DisplayName("")
//    void deleteUser() {
//    }
}