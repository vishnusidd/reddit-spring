package com.example.redditspring.service;

import com.example.redditspring.dto.RegisterRequest;
import com.example.redditspring.exception.SpringRedditException;
import com.example.redditspring.model.NotificationEmail;
import com.example.redditspring.model.User;
import com.example.redditspring.model.VerificationToken;
import com.example.redditspring.repository.UserRepository;
import com.example.redditspring.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) throws SpringRedditException {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        try {
            mailService.sendMail(new NotificationEmail("please activate your account", user.getEmail(),
                    " thank you for sign up  click the link  " +
                            "http://localhost:8080/api/auth/accountVerification/" + token));
        } catch (Exception e) {
            System.out.println("caught");
            throw new SpringRedditException("Exception occurred when sending mail ");
        }
    }

     private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
         VerificationToken verificationToken = new VerificationToken();
         verificationToken.setToken(token);
         verificationToken.setUser(user);
         verificationTokenRepository.save(verificationToken);
         return token;
    }
}
