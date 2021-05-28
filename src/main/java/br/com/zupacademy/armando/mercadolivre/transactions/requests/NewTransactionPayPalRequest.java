package br.com.zupacademy.armando.mercadolivre.transactions.requests;

import br.com.zupacademy.armando.mercadolivre.core.validations.ExistsBy;
import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.transactions.validations.CorrectGateway;
import br.com.zupacademy.armando.mercadolivre.transactions.validations.DuplicateSuccessTransaction;
import br.com.zupacademy.armando.mercadolivre.transactions.validations.PurchaseIsFinalized;

import javax.validation.constraints.NotBlank;

public class NewTransactionPayPalRequest {

    @NotBlank @ExistsBy(entityClass = Purchase.class, fieldName = "code") @PurchaseIsFinalized() @CorrectGateway(gateway = "PayPal")
    private String purchaseCode;
    @NotBlank @DuplicateSuccessTransaction()
    private String transactionCode;
    @NotBlank
    private String status;

    public NewTransactionPayPalRequest(String purchaseCode, String transactionCode, String status) {
        this.purchaseCode = purchaseCode;
        this.transactionCode = transactionCode;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

}
