package br.com.zupacademy.armando.mercadolivre.users.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

}
