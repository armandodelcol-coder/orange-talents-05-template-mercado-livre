package br.com.zupacademy.armando.mercadolivre.users.controllers;

import br.com.zupacademy.armando.mercadolivre.config.security.TokenService;
import br.com.zupacademy.armando.mercadolivre.users.requests.AuthRequest;
import br.com.zupacademy.armando.mercadolivre.users.responses.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/api/auth")
    public ResponseEntity<TokenResponse> auth(@RequestBody @Valid AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authData = authRequest.toAuthToken();

        try {
            Authentication authentication = authenticationManager.authenticate(authData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
