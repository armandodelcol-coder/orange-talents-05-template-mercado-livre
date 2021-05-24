package br.com.zupacademy.armando.mercadolivre.categories.requests;

import br.com.zupacademy.armando.mercadolivre.categories.entities.Category;
import br.com.zupacademy.armando.mercadolivre.core.validations.ExistsId;
import br.com.zupacademy.armando.mercadolivre.core.validations.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank @UniqueValue(entityClass = Category.class, fieldName = "name")
    private String name;
    @ExistsId(entityClass = Category.class)
    private Long parentCategoryId;

    public NewCategoryRequest(String name, Long parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public Category toModel(EntityManager entityManager) {
        Category category = new Category(this.name);
        if (parentCategoryId != null) {
            Category parentCategory = entityManager.find(Category.class, this.parentCategoryId);
            category.setParentCategory(parentCategory);
        }
        return category;
    }

}
