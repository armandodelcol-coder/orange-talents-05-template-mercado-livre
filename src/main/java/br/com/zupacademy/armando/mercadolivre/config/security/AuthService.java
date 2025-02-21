package br.com.zupacademy.armando.mercadolivre.config.security;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import br.com.zupacademy.armando.mercadolivre.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) return user.get();

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }

}
