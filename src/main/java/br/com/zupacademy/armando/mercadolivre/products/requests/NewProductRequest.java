package br.com.zupacademy.armando.mercadolivre.products.requests;

import br.com.zupacademy.armando.mercadolivre.categories.entities.Category;
import br.com.zupacademy.armando.mercadolivre.core.validations.ExistsBy;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewProductRequest {

    @NotBlank
    private String name;
    @NotNull @Positive
    private BigDecimal price;
    @NotNull @PositiveOrZero
    private Integer stock;
    @NotBlank @Length(max = 1000)
    private String description;
    @NotNull @ExistsBy(entityClass = Category.class, fieldName = "id")
    private Long categoryId;
    @NotNull @Size(min = 3) @Valid
    private List<AttributeRequest> attributes = new ArrayList<>();

    public NewProductRequest(String name,
                             BigDecimal price,
                             Integer stock,
                             String description,
                             Long categoryId,
                             List<AttributeRequest> attributes) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.categoryId = categoryId;
        this.attributes = attributes;
    }

    public List<AttributeRequest> getAttributes() {
        return attributes;
    }

    public Product toModel(EntityManager entityManager, User user) {
        Assert.state(categoryId != null, "categoryId precisa existir para criar um Produto");
        Assert.notNull(user, "user não pode ser nulo para criar um Produto");

        Category category = entityManager.find(Category.class, this.categoryId);
        Assert.state(category != null, "Categoria precisa existir para criar um Produto");

        Product newProduct = new Product(
                this.name,
                this.price,
                this.stock,
                this.description,
                category,
                attributes,
                user
        );
        return newProduct;
    }

    public List<String> findDuplicateAttributesNames() {
        Set<String> duplicateAttributesNames = new HashSet<>();
        Set<String> attributesNames = new HashSet<>();
        this.attributes.forEach(attr -> {
            if (!attributesNames.add(attr.getName())) {
                duplicateAttributesNames.add(attr.getName());
            }
        });
        return new ArrayList<>(duplicateAttributesNames);
    }

}
