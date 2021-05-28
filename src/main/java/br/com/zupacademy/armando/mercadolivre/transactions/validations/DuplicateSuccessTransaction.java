package br.com.zupacademy.armando.mercadolivre.transactions.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { DuplicateSuccessTransactionValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface DuplicateSuccessTransaction {

    String message() default "Essa transação já foi efetuada com sucesso para outra compra.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
