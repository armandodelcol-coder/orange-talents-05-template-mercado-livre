package br.com.zupacademy.armando.mercadolivre.products.controllers;

import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.products.responses.ProductDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
public class ProductDetailsController {

    private EntityManager entityManager;

    public ProductDetailsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/api/produtos/{id}")
    public ResponseEntity<ProductDetailsResponse> findById(@PathVariable Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ProductDetailsResponse(product));
    }

}
