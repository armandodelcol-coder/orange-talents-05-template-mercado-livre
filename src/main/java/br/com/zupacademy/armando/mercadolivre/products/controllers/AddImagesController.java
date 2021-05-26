package br.com.zupacademy.armando.mercadolivre.products.controllers;

import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.products.requests.NewImagesRequest;
import br.com.zupacademy.armando.mercadolivre.core.imageuploaders.ImageUploader;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class AddImagesController {

    private ImageUploader imageUploader;
    private EntityManager entityManager;

    public AddImagesController(ImageUploader imageUploader,
                               EntityManager entityManager) {
        this.imageUploader = imageUploader;
        this.entityManager = entityManager;
    }

    @PostMapping("/api/produtos/{id}/imagens")
    @Transactional
    public ResponseEntity<?> upload(@PathVariable Long id,
                                         @Valid NewImagesRequest newImagesRequest,
                                         @AuthenticationPrincipal User user) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        if (!product.belongsTo(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<String> links = imageUploader.send(newImagesRequest.getImages());
        product.associateImages(links);
        entityManager.merge(product);
        return ResponseEntity.ok().build();
    }

}
