package br.com.zupacademy.armando.mercadolivre.core.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String jpql = "select entity from " + entityClass.getName() + " entity where " + fieldName + " = :value";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("value", value);
        List<?> registers = query.getResultList();
        return registers.isEmpty();
    }

}
