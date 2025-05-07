package org.fastcampus.acceptance.auth;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.fastcampus.acceptance.steps.SingUpAcceptanceSteps.requestSendMail;
import static org.junit.jupiter.api.Assertions.*;

public class SingAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";
    @BeforeEach
    void setUp(){
        this.cleanUp();
    }

    @Test
    @DisplayName("메일을 전송하고 토큰을 저장한다")
    public void givenEmail_whenSendEmail_thenVerificationTokenSaved() throws Exception{
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = requestSendMail(dto);
        //then
        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    @DisplayName("유효하지 않은 이메일을 보내면 실패한다")
    public void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() throws Exception{
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto("abc");

        //when
        Integer code = requestSendMail(dto);

        //then
        String token = this.getEmailToken(email);
        assertNull(token);
        assertNotEquals(500, code);
    }
}
