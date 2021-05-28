package br.com.zupacademy.armando.mercadolivre.core.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { ExistsByValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ExistsBy {

    String fieldName();

    Class<?> entityClass();

    String message() default "{javax.validation.constraints.ExistsBy.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
