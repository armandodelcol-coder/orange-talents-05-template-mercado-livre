package br.com.zupacademy.armando.mercadolivre.transactions.validations;

import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.purchases.enums.Gateway;
import br.com.zupacademy.armando.mercadolivre.purchases.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectGatewayValidator implements ConstraintValidator<CorrectGateway, String> {

    @Autowired
    private PurchaseRepository purchaseRepository;

    private String gateway;

    @Override
    public void initialize(CorrectGateway constraintAnnotation) {
        this.gateway = constraintAnnotation.gateway();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        Purchase purchase = purchaseRepository.getByCode(value);
        if (purchase.getGateway().equals(Gateway.valueOf(gateway))) {
            return true;
        }

        return false;
    }

}
