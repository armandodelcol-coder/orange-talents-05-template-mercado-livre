package br.com.zupacademy.armando.mercadolivre.transactions.validations;

import br.com.zupacademy.armando.mercadolivre.transactions.entities.Transaction;
import br.com.zupacademy.armando.mercadolivre.transactions.enums.TransactionStatus;
import br.com.zupacademy.armando.mercadolivre.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class DuplicateSuccessTransactionValidator implements ConstraintValidator<DuplicateSuccessTransaction, String> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        Optional<Transaction> possibleTransaction = transactionRepository.findByTransactionCodeAndStatus(value, TransactionStatus.Sucesso);
        if (possibleTransaction.isPresent()) {
            return false;
        }

        return true;
    }

}
