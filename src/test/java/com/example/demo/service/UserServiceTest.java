package com.example.demo.service;

import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.model.user.User;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    private String name;
    private String email;
    private String password;

    @BeforeAll
    void setUp() {
        name = "test0";
        email = "test0@gmail.com";
        password = "1234";
    }

    @Test
    void 사용자_이메일로_조회() {
        assertTrue(userService.isEmailExist(email));
    }

    @Test
    void 사용자_로그인_성공() {
        User user = userService.loginUser(email, password);
        assertThat(user, Is.is(CoreMatchers.notNullValue()));
        assertThat(user.getEmail(), Is.is(email));
    }

    @Test
    @DisplayName("잘못된 비밀번호로 로그인 시도하면 실패")
    void 사용자_잘못된_비밀번호_로그인_실패() {
        assertThatThrownBy(()-> userService.loginUser(email, "1"))
                .isInstanceOf(UnauthenticatedException.class);
    }
}









