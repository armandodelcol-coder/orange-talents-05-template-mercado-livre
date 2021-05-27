package br.com.zupacademy.armando.mercadolivre.purchases.requests;

import br.com.zupacademy.armando.mercadolivre.core.validations.ExistsId;
import br.com.zupacademy.armando.mercadolivre.core.validations.ValueOfEnum;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.purchases.enums.Gateway;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewPurchaseRequest {

    @NotNull @Positive
    private Integer quantity;
    @NotNull @ValueOfEnum(enumClass = Gateway.class)
    private String gateway;
    @NotNull @ExistsId(entityClass = Product.class)
    private Long productId;

    public NewPurchaseRequest(Integer quantity, String gateway, Long productId) {
        this.quantity = quantity;
        this.gateway = gateway;
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Purchase toModel(Product product, User user) {
        return new Purchase(this.quantity, Gateway.valueOf(this.gateway), product, user);
    }

}
