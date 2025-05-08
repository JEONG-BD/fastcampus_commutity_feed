package org.fastcampus.auth;

import org.fastcampus.auth.domain.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email){
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "natty#22@", "안녕하세요"})
    void givenInvalidEmail_whenCreate_thenThrowError(String email){
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@ab.com", "3232@naver.com", "natty22@test.com", "test@test.com"})
    void givenValidEmail_whenCreate_thenThrowError(String email){

        Email emailValue = Email.createEmail(email);
        assertEquals(email, emailValue.getEmailText());
    }
}

