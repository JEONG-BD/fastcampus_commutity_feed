package org.fastcampus.auth.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.fastcampus.auth.application.interfaces.EmailSendRepository;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.domain.RandomTokenGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSendRepository emailSendRepository;

    public void sendEmail(SendEmailRequestDto dto){
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);

    }

    public void verifyEmail(String email, String token){
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue, token);
    }
}
