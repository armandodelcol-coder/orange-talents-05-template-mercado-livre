package br.com.zupacademy.armando.mercadolivre.transactions.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { CorrectGatewayValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface CorrectGateway {

    String gateway();

    String message() default "Essa compra foi gerada com outro Gateway de pagamento.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
