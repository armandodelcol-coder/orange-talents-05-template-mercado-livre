package br.com.zupacademy.armando.mercadolivre.core.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsByValidator implements ConstraintValidator<ExistsBy, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String fieldName;

    private Class<?> entityClass;

    @Override
    public void initialize(ExistsBy constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String jpql = "select entity from " + entityClass.getName() + " entity where " + fieldName + " = :value";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("value", value);
        List<?> entities = query.getResultList();
        return !entities.isEmpty();
    }

}
