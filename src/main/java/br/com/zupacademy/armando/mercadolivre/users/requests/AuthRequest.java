package br.com.zupacademy.armando.mercadolivre.users.requests;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthRequest {

    @Email @NotBlank
    private String email;
    @NotBlank @Length(min = 6)
    private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UsernamePasswordAuthenticationToken toAuthToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
