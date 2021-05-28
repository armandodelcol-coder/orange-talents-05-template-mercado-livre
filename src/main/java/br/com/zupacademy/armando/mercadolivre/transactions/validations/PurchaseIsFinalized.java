package br.com.zupacademy.armando.mercadolivre.transactions.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { PurchaseIsFinalizedValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface PurchaseIsFinalized {

    String message() default "O pagamento já foi efetuado com sucesso para essa compra.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
