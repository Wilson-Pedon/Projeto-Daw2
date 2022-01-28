package com.example.trabalhodaw2.SecurityConfigurations;

import com.example.trabalhodaw2.Dao.ClienteRepositorio;
import com.example.trabalhodaw2.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ClienteRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente = Optional.ofNullable(repositorio.findByEmail(username));
        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new UsernameNotFoundException("Dados Invalidos");
    }
}
