package com.example.trabalhodaw2.Controller;

import com.example.trabalhodaw2.Model.Cliente;
import com.example.trabalhodaw2.Model.Lance;
import com.example.trabalhodaw2.Service.ClienteService;
import com.example.trabalhodaw2.Service.LanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/api/cliente", method = RequestMethod.GET)
    public List<Cliente> ListarTodosLance(){
        return service.BuscaTodos();
    }

    @RequestMapping(value = "/api/cliente/{id}", method = RequestMethod.GET)
    public Cliente LanceEspecifico(@PathVariable(value = "id") long id){
        return service.BuscaClienteUnico(id);
    }

    @RequestMapping(value = "/api/cliente", method = RequestMethod.POST)
    public Cliente Post(@Valid @RequestBody Cliente cliente) {
        return service.CadastrarCliente(cliente);
    }

    @RequestMapping(value = "/api/cliente/{id}", method = RequestMethod.PUT)
    public Cliente LanceEditar(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newLance){
        return service.AtulizarCliente(id,newLance);
    }

    @RequestMapping(value = "/api/cliente/{id}", method = RequestMethod.DELETE)
    public HttpEntity<? extends Serializable> DeletarLance(@PathVariable(value = "id")long id){
        return service.DeletarCliente(id);
    }

}
