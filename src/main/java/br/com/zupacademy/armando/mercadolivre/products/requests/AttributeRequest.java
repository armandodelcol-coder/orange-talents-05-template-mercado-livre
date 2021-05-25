package br.com.zupacademy.armando.mercadolivre.products.requests;

import br.com.zupacademy.armando.mercadolivre.products.entities.Attribute;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;

import javax.validation.constraints.NotBlank;

public class AttributeRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public AttributeRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Attribute toModel(Product product) {
        return new Attribute(this.name, this.description, product);
    }

}
