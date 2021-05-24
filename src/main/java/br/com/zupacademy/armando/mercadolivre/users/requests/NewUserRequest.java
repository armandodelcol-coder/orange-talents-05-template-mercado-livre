package br.com.zupacademy.armando.mercadolivre.users.requests;

import br.com.zupacademy.armando.mercadolivre.core.validations.UniqueValue;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import br.com.zupacademy.armando.mercadolivre.users.models.RawPassword;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUserRequest {

    @NotBlank @Email @UniqueValue(entityClass = User.class, fieldName = "email")
    private String email;
    @NotBlank @Length(min = 6)
    private String password;

    public NewUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toModel() {
        return new User(this.email, new RawPassword(this.password));
    }

}
