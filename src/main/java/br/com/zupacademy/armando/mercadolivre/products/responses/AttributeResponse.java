package br.com.zupacademy.armando.mercadolivre.products.responses;

import br.com.zupacademy.armando.mercadolivre.products.entities.Attribute;

public class AttributeResponse {

    private String name;
    private String description;

    public AttributeResponse(Attribute attribute) {
        this.name = attribute.getName();
        this.description = attribute.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
