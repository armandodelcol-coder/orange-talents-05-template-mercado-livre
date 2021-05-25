package br.com.zupacademy.armando.mercadolivre.products.controllers;

import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.products.requests.NewProductRequest;
import br.com.zupacademy.armando.mercadolivre.products.validations.DuplicateAttributeValidator;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewProductController {

    @Autowired
    private DuplicateAttributeValidator duplicateAttributeValidator;

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(duplicateAttributeValidator);
    }

    @PostMapping("/api/produtos")
    @Transactional
    public void register(@RequestBody @Valid NewProductRequest newProductRequest, @AuthenticationPrincipal User user) {
        Product newProduct = newProductRequest.toModel(entityManager, user);
        entityManager.persist(newProduct);
    }

}
