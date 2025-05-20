package org.fastcampus.acceptance.utils;

import org.fastcampus.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseClaenUp claenUp;

    @Autowired
    private DataLoader dataLoader;

    @BeforeEach
    public void init(){
        claenUp.execute();
        dataLoader.loadData();;
    }

    protected void cleanUp(){
        claenUp.execute();
    }

    protected String getEmailToken(String email){
        return dataLoader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email){
        return dataLoader.isEmailVerified(email);
    }

    protected Long getUserId(String email){
        return dataLoader.getUserId(email);
    }

    protected void createUser(String email){
        dataLoader.createUser(email);
    }

    protected String login(String email){
        return requestLoginGetToken(new LoginRequestDto(email, "password"));
    }
}
