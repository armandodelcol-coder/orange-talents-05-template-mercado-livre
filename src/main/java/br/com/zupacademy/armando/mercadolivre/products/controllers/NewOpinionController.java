package br.com.zupacademy.armando.mercadolivre.products.controllers;

import br.com.zupacademy.armando.mercadolivre.products.entities.Opinion;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.products.requests.NewOpinionRequest;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewOpinionController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/produtos/{id}/opinioes")
    @Transactional
    public ResponseEntity<?> register(@PathVariable("id") Long productId,
                                           @RequestBody @Valid NewOpinionRequest newOpinionRequest,
                                           @AuthenticationPrincipal User user) {
        Product product = entityManager.find(Product.class, productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        Opinion newOpinion = newOpinionRequest.toModel(product, user);
        entityManager.persist(newOpinion);
        return ResponseEntity.ok().build();
    }

}
