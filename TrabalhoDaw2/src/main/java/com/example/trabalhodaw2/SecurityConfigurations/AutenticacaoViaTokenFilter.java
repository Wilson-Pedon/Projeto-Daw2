package com.example.trabalhodaw2.SecurityConfigurations;

import com.example.trabalhodaw2.Dao.ClienteRepositorio;
import com.example.trabalhodaw2.Model.Cliente;
import com.example.trabalhodaw2.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.Optional;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {


    private TokenService tokenService;
    private ClienteRepositorio repositorio;

    public AutenticacaoViaTokenFilter(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public AutenticacaoViaTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public AutenticacaoViaTokenFilter(TokenService tokenService, ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.TokenValido(token);
        if(valido){
            autenticarCliente(token);
        }

        filterChain.doFilter(request,response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Cliente cliente = repositorio.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }

}
