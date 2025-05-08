package org.fastcampus.auth;

import org.fastcampus.auth.domain.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PasswordTest {

    @Test
    @DisplayName("패스워드를 입력했을 때 같으면 TRUE를 반환한다.")
    public void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        //given
        Password password = Password.createEncryptPassword("password");
        //when
        //then
        Assertions.assertTrue(password.matchPassword("password"));
    }

    @Test
    @DisplayName("잘못된 패스워드를 입력할 때 FALSE를 반환한다")
    public void givenPassword_whenMatchDiffPassword_thenReturnTrue()  throws Exception{
        //given
        Password password = Password.createEncryptPassword("password");
        //when
        //then
        Assertions.assertFalse(password.matchPassword("password1"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("패스워드에 Null을 입력하면 에러를 반환하다.")
    public void givenPasswordNulls_thenThrowError(String password) throws Exception{
        //given
        Assertions.assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
        //when

        //then
    }
}
