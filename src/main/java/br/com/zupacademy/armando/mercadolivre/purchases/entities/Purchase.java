package br.com.zupacademy.armando.mercadolivre.purchases.entities;

import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.purchases.enums.Gateway;
import br.com.zupacademy.armando.mercadolivre.purchases.enums.PurchaseStatus;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    @Column(columnDefinition = "smallint unsigned", nullable = false)
    private Integer quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gateway gateway;

    @NotNull @Valid
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull @Valid
    @ManyToOne
    @JoinColumn(name = "purchaser_id", nullable = false)
    private User purchaser;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "decimal", nullable = false, scale = 10, precision = 2)
    private BigDecimal currentProductPrice;

    @Column(nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseStatus status = PurchaseStatus.INICIADA;

    public Purchase(Integer quantity, Gateway gateway, Product product, User purchaser) {
        this.quantity = quantity;
        this.gateway = gateway;
        this.product = product;
        this.purchaser = purchaser;
    }

    @PrePersist
    private void prePersist() {
        this.currentProductPrice = this.product.getPrice();
        this.code = UUID.randomUUID().toString();
    }

    public String getPurchaserName() {
        return this.purchaser.getUsername();
    }

    public String getProductName() {
        return this.product.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductOwnerName() {
        return this.product.getOwner().getUsername();
    }

    public String getGatewayUrl() {
        return this.gateway.getUrl(this.code);
    }

}
