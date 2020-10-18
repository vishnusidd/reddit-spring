package com.example.redditspring.controller;

import com.example.redditspring.dto.RegisterRequest;
import com.example.redditspring.exception.SpringRedditException;
import com.example.redditspring.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;

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

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws SpringRedditException {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Acttivated successfuly", HttpStatus.OK);
    }
}

