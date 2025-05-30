package org.fastcampus.acceptance.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.springframework.stereotype.Component;

import static org.fastcampus.acceptance.steps.SingUpAcceptanceSteps.*;
import static org.fastcampus.acceptance.steps.UserAcceptanceStep.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceStep.followUser;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData(){
        //UserCreateRequestDto dto = new UserCreateRequestDto("test user", "");
        //createUser(dto);
        //createUser(dto);
        //createUser(dto);
        for (int i = 0; i <4 ; i++) {
            createUser("user" + i + "@test.com");
        }
        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));
    }

    public String getEmailToken(String email){
        return entityManager.createNativeQuery("SELECT token " +
                "FROM community_email_verification " +
                "WHERE email = ? " , String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email){
        return entityManager.createQuery("SELECT isVerified " +
                "FROM EmailVerificationEntity " +
                "WHERE email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Long getUserId(String email){
        return (Long)entityManager.createNativeQuery("SELECT userId " +
                        "FROM community_user_auth " +
                        "WHERE email = ? " , Long.class)
                .setParameter(1, email)
                .getSingleResult();
    }

    public void createUser(String email){
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email,"password", "name", "USER", ""));

    }
}
