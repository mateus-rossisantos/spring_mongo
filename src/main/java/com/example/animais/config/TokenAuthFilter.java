package com.example.animais.config;

import com.example.animais.model.Usuario;
import com.example.animais.repository.UsuarioRepository;
import com.example.animais.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository userRepository;

    public TokenAuthFilter(TokenService tokenService, UsuarioRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido) authUser(token);
        filterChain.doFilter(request, response);
    }

    private void authUser(String token) {
        Long idUsuario = tokenService.getIdUser(token);
        Usuario usuario = userRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,
                    null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        boolean tokenNaoExiste = (token == null || !token.startsWith("Bearer "));
        if (tokenNaoExiste) return null;
        return token.substring(7);
    }
}
