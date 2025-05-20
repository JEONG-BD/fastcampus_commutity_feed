package org.fastcampus.auth;

import org.fastcampus.auth.domain.TokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenProviderTest {
    private final TokenProvider tokenProvider = new TokenProvider("asdaasdaasdaasdaasdaasdaasdaasdaasdaasdaasdaasdaasdasdasdasdasdasdasdasdasdasdasdasdasdasdasda");

    @Test
    void givenValidUserIdAndRole_whenCreateToken_thenReturnValidToken() {
        // given
        Long userId = 1L;
        String role = "ADMIN";

        // when
        String token = tokenProvider.createToken(userId, role);

        // then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getRoles(token));
    }

    @Test
    void givenInvalidToken_whenGetUserId_thenThrowException() {
        // given
        String invalidToken = "invalid.token.here";

        // when, then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }

    @Test
    void givenInvalidToken_whenGetRoles_thenThrowException() {
        // given
        String invalidToken = "invalid.token.here";

        // when, then
        assertThrows(Exception.class, () -> tokenProvider.getRoles(invalidToken));
    }

    @Test
    void givenValidToken_whenGetUserId_thenReturnCorrectUserId() {
        // given
        Long userId = 1L;
        String role = "USER";
        String token = tokenProvider.createToken(userId, role);

        // when
        Long retrievedUserId = tokenProvider.getUserId(token);

        // then
        assertEquals(userId, retrievedUserId);
    }

    @Test
    void givenValidToken_whenGetRoles_thenReturnCorrectRole() {
        // given
        Long userId = 1L;
        String role = "ADMIN";
        String token = tokenProvider.createToken(userId, role);

        // when
        String retrievedRole = tokenProvider.getRoles(token);

        // then
        assertEquals(role, retrievedRole);
    }
}
