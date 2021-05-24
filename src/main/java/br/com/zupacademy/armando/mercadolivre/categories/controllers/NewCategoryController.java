package br.com.zupacademy.armando.mercadolivre.categories.controllers;

import br.com.zupacademy.armando.mercadolivre.categories.entities.Category;
import br.com.zupacademy.armando.mercadolivre.categories.requests.NewCategoryRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewCategoryController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/categorias")
    @Transactional
    public void register(@RequestBody @Valid NewCategoryRequest newCategoryRequest) {
        Category newCategory = newCategoryRequest.toModel(entityManager);
        entityManager.persist(newCategory);
    }

}
