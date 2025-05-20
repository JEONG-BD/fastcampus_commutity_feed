package org.fastcampus.common.principan;

import lombok.Getter;
import org.fastcampus.auth.domain.UserRole;

@Getter
public class UserPrincipal {
    private Long userId;
    private UserRole role;

    public UserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.role = UserRole.valueOf(role);
    }
}
