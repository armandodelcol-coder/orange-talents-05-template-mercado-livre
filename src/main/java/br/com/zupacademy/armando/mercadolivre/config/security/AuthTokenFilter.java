package br.com.zupacademy.armando.mercadolivre.config.security;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import br.com.zupacademy.armando.mercadolivre.users.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);
        boolean valid = this.tokenService.isValidToken(token);
        if (valid) {
            authUser(token);
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank() || !token.startsWith("Bearer")) return null;

        return token.substring(7, token.length());
    }

    private void authUser(String token) {
        Long userId = tokenService.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
