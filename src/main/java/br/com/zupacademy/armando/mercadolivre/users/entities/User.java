package br.com.zupacademy.armando.mercadolivre.users.entities;

import br.com.zupacademy.armando.mercadolivre.users.models.RawPassword;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> authorities = new ArrayList<>();

    @Deprecated
    public User() {
    }

    public User(String email, RawPassword rawPassword) {
        this.email = email;
        this.password = rawPassword.getPassword();
    }

    public Long getId() {
        return id;
    }

    @PrePersist
    private void prePersist() {
        this.password = encodingPassword(this.password);
    }

    private String encodingPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(rawPassword);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
