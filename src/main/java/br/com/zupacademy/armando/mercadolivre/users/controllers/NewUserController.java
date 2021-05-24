package br.com.zupacademy.armando.mercadolivre.users.controllers;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import br.com.zupacademy.armando.mercadolivre.users.requests.NewUserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewUserController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/usuarios")
    @Transactional
    public void register(@RequestBody @Valid NewUserRequest newUserRequest) {
        User newUser = newUserRequest.toModel();
        entityManager.persist(newUser);
    }

}
