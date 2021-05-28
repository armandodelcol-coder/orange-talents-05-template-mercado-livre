package br.com.zupacademy.armando.mercadolivre.transactions.repository;

import br.com.zupacademy.armando.mercadolivre.transactions.entities.Transaction;
import br.com.zupacademy.armando.mercadolivre.transactions.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByPurchaseCodeAndStatus(String code, TransactionStatus status);
    Optional<Transaction> findByTransactionCodeAndStatus(String code, TransactionStatus status);

}
