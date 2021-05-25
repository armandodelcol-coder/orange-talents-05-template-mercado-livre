package br.com.zupacademy.armando.mercadolivre.products.validations;

import br.com.zupacademy.armando.mercadolivre.products.requests.NewProductRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class DuplicateAttributeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(NewProductRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) return;

        NewProductRequest newProductRequest = (NewProductRequest) o;
        List<String> duplicateAttributesNames = newProductRequest.findDuplicateAttributesNames();
        if (duplicateAttributesNames.isEmpty()) return;

        errors.rejectValue("attributes",
                "DuplicateAttribute",
                "Existem atributos duplicados de nome: " + duplicateAttributesNames.toString());
    }

}
