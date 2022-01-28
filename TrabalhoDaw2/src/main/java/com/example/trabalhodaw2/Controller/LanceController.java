package com.example.trabalhodaw2.Controller;


import com.example.trabalhodaw2.Model.Lance;
import com.example.trabalhodaw2.Service.LanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class LanceController {

    @Autowired
    private LanceService service;

    @RequestMapping(value = "/api/lance", method = RequestMethod.GET)
    public List<Lance> ListarTodosLance(){
        return service.BuscaTodos();
    }

    @RequestMapping(value = "/api/lancel/{id}", method = RequestMethod.GET)
    public List<Lance> LanceEspecificoLeilao(@PathVariable(value = "id") long id){
        return service.BuscaLanceApartirDoIdLeilao(id);
    }

    @RequestMapping(value = "/api/lance/{id}", method = RequestMethod.GET)
    public Lance LanceEspecifico(@PathVariable(value = "id") long id){
        return service.BuscaLanceUnico(id);
    }

    @RequestMapping(value = "/api/lance", method = RequestMethod.POST)
    public Lance Post(@Valid @RequestBody Lance lance) {
        return service.CadastrarLance(lance);
    }

    @RequestMapping(value = "/api/lance/{id}", method = RequestMethod.PUT)
    public Lance LanceEditar(@PathVariable(value = "id") long id, @Valid @RequestBody Lance newLance){
        return service.AtulizarLance(id,newLance);
    }
    @RequestMapping(value = "/api/lance/{id}", method = RequestMethod.DELETE)
    public HttpEntity<? extends Serializable> DeletarLance(@PathVariable(value = "id")long id){
        return service.DeletarLeilao(id);
    }


}
