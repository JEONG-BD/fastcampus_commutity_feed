package org.fastcampus.acceptance.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.springframework.stereotype.Component;

import static org.fastcampus.acceptance.steps.UserAcceptanceStep.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceStep.followUser;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData(){
        UserCreateRequestDto dto = new UserCreateRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

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
}
