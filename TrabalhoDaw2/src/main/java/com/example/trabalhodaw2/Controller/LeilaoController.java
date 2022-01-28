package com.example.trabalhodaw2.Controller;

import com.example.trabalhodaw2.Model.Leilao;
import com.example.trabalhodaw2.Service.LeilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins="", allowedHeaders = "")
@RestController
public class LeilaoController {

    @Autowired
    private LeilaoService service;

    @RequestMapping(value = "/api/leiloes", method = RequestMethod.GET)
    public List<Leilao> ListarTodosLeiloes(){
        return service.BuscaTodos();
    }

    @RequestMapping(value = "/api/leilao/{id}", method = RequestMethod.GET)
    public Leilao LeilaoEspecifico(@PathVariable(value = "id") long id){
        return service.BuscaLeilaoUnico(id);
    }

    @RequestMapping(value = "/api/leiloesAberto", method = RequestMethod.GET)
    public List<Leilao> ListarTodosLeiloesStatusAberto(){
        return service.BuscaLeilaoStatus();
    }

    @RequestMapping(value = "/api/leilao", method = RequestMethod.POST)
    public Leilao Post(@Valid @RequestBody Leilao leilao) {
        return service.CadastrarLeilao(leilao);
    }

    @RequestMapping(value = "/api/leilao/{id}", method = RequestMethod.PUT)
    public Leilao LeilaoEditar(@PathVariable(value = "id") long id, @Valid @RequestBody Leilao newLeilao){
       return service.AtulizarLeilao(id,newLeilao);
    }

    @RequestMapping(value = "/api/leilao/{id}", method = RequestMethod.DELETE)
    public HttpEntity<? extends Serializable> DeletarLeilao(@PathVariable(value = "id")long id){
        return service.DeletarLeilao(id);
    }

    @RequestMapping(value = "/api/leilaoStatus/{status}", method = RequestMethod.GET)
    public List<Leilao> BuscaLeilaoStatus(@PathVariable(value = "status") String status) {
        return service.BuscaLeilaoStatus(status);
    }

}