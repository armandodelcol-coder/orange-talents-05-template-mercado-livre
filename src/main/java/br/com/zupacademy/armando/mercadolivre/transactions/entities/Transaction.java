package br.com.zupacademy.armando.mercadolivre.transactions.entities;

import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.purchases.enums.Gateway;
import br.com.zupacademy.armando.mercadolivre.transactions.enums.TransactionStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @NotNull @Valid
    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @NotBlank
    @Column(nullable = false)
    private String transactionCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gateway gateway;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @Deprecated
    public Transaction() {
    }

    public Transaction(TransactionStatus status, Purchase purchase, String transactionCode, Gateway gateway) {
        this.status = status;
        this.purchase = purchase;
        this.transactionCode = transactionCode;
        this.gateway = gateway;
    }

    public String getPurchaserEmail() {
        return this.purchase.getPurchaserName();
    }

    public String getProductName() {
        return this.purchase.getProductName();
    }

    public Integer getProductQuantity() {
        return this.purchase.getQuantity();
    }

    public String getProductOwner() {
        return this.purchase.getProductOwnerName();
    }

}
