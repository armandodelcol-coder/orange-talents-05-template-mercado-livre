package br.com.zupacademy.armando.mercadolivre.purchases.repository;

import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Purchase getByCode(String code);

}
