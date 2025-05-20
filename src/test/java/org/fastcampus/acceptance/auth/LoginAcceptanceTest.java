package org.fastcampus.acceptance.auth;


import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.LoginRequestDto;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetCode;
import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.fastcampus.acceptance.steps.SingUpAcceptanceSteps.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";
    //private final TokenProvider tokenProvider = new TokenProvider("testteststestteststestteststestteststestteststestteststestteststestteststestteststesttests");
    @BeforeEach
    void setUp(){
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenToken() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password");

        // when
        String token = requestLoginGetToken(dto);

        // then
        assertNotNull(token);
    //    Long id = tokenProvider(token);
    //    assertEquals(1L, id);
    }

    @Test
    void givenWrongPassword_whenLogin_thenException() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wrongPassword");

        // when
        Integer code = requestLoginGetCode(dto);

        // then
        assertEquals(500, code);
    }

}
