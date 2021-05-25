package br.com.zupacademy.armando.mercadolivre.users.repository;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
