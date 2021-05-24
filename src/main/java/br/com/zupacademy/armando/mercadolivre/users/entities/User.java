package br.com.zupacademy.armando.mercadolivre.users.entities;

import br.com.zupacademy.armando.mercadolivre.users.models.RawPassword;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank @Length(min = 6)
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDateTime createdAt;

    @Deprecated
    public User() {
    }

    public User(String email, RawPassword rawPassword) {
        this.email = email;
        this.password = rawPassword.getPassword();
    }

    @PrePersist
    private void prePersist() {
        this.password = encodingPassword(this.password);
    }

    private String encodingPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(rawPassword);
    }

}
