package br.com.zupacademy.armando.mercadolivre.products.controllers;

import br.com.zupacademy.armando.mercadolivre.core.emaildispatchers.EmailDispatcherToProductOwner;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.products.entities.Question;
import br.com.zupacademy.armando.mercadolivre.products.requests.NewQuestionRequest;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewQuestionController {

    private EntityManager entityManager;
    private EmailDispatcherToProductOwner emailDispatcherToProductOwner;

    public NewQuestionController(EntityManager entityManager,
                                 EmailDispatcherToProductOwner emailDispatcherToProductOwner) {
        this.entityManager = entityManager;
        this.emailDispatcherToProductOwner = emailDispatcherToProductOwner;
    }

    @PostMapping("/api/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> register(@PathVariable("id") Long productId,
                                      @RequestBody @Valid NewQuestionRequest newQuestionRequest,
                                      @AuthenticationPrincipal User user) {
        Product product = entityManager.find(Product.class, productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        Question newQuestion = newQuestionRequest.toModel(product, user);
        entityManager.persist(newQuestion);
        emailDispatcherToProductOwner.sendQuestion(newQuestion);
        return ResponseEntity.ok().build();
    }

}
