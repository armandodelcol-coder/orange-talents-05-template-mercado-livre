package br.com.zupacademy.armando.mercadolivre.purchases.controllers;

import br.com.zupacademy.armando.mercadolivre.core.emaildispatchers.EmailDispatcherToProductOwner;
import br.com.zupacademy.armando.mercadolivre.core.usefulclasses.ErrorResponseBody;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.purchases.requests.NewPurchaseRequest;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewPurchaseController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailDispatcherToProductOwner emailDispatcherToProductOwner;

    @PostMapping("/api/comprar")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest,
                                      @AuthenticationPrincipal User user) {
        Product product = entityManager.find(Product.class, newPurchaseRequest.getProductId());
        if (product.withDrawStock(newPurchaseRequest.getQuantity())) {
            Purchase newPurchase = newPurchaseRequest.toModel(product, user);
            entityManager.persist(newPurchase);
            entityManager.merge(product);
            emailDispatcherToProductOwner.sendPurchaseNotification(newPurchase);
            return ResponseEntity.status(HttpStatus.MOVED_TEMPORARILY).body(newPurchase.getGatewayUrl());
        }

        return ResponseEntity.badRequest()
                .body(new ErrorResponseBody(
                        "Quantidade indisponível",
                        "Estoque do produto indisponível para quantidade desejada, estoque disponível: " + product.getStock()
                ));
    }

}
