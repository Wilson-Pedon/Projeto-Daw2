package com.example.trabalhodaw2.Service;

import com.example.trabalhodaw2.Dao.ClienteRepositorio;
import com.example.trabalhodaw2.Model.Cliente;
import com.example.trabalhodaw2.Model.Lance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio ClienteRepositorio;



    public List<Cliente> BuscaTodos() {
        return ClienteRepositorio.findAll();
    }

    public Cliente BuscaClienteUnico(long id) {
        return ClienteRepositorio.findById(id);
    }

    public Cliente CadastrarCliente(Cliente cliente) {
        return ClienteRepositorio.save(cliente);
    }

    public Cliente AtulizarCliente(long id, Cliente newLance) {
        Cliente clienteAntigo = ClienteRepositorio.findById(id);
        Cliente cliente = clienteAntigo;
        cliente.setNome(newLance.getNome());
        cliente.setEmail(newLance.getEmail());
        cliente.setSenha(newLance.getSenha());
        return ClienteRepositorio.save(cliente);
    }

    public HttpEntity<? extends Serializable> DeletarCliente(long id) {
        Cliente cliente = ClienteRepositorio.findById(id);
        if(cliente != null){
            ClienteRepositorio.deleteById(id);
            return new ResponseEntity<Lance>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Lance>(HttpStatus.NOT_FOUND);
        }
    }

    public Cliente BuscaEmailDoUtimoUsuarioDoLance(String nome){
        return ClienteRepositorio.findByEmail(nome);
    }

}
