package br.com.zupacademy.armando.mercadolivre.core.validations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String jpql = "select entity from " + entityClass.getName() + " entity where id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", value);
        List<?> entities = query.getResultList();
        return !entities.isEmpty();
    }

}
