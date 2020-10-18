package com.example.redditspring.controller;

import com.example.redditspring.dto.RegisterRequest;
import com.example.redditspring.exception.SpringRedditException;
import com.example.redditspring.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws SpringRedditException {
        try {
            authService.signup(registerRequest);
            return new ResponseEntity<>("user registeraton successful", HttpStatus.OK);
        } catch(Exception e ){

            throw new SpringRedditException("Exception occurred when sending mail ");
        }
    }
}

