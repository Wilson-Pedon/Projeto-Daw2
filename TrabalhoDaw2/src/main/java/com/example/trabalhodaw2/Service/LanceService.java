package com.example.trabalhodaw2.Service;

import com.example.trabalhodaw2.Dao.LanceRepositorio;
import com.example.trabalhodaw2.Dao.LeilaoRepositorio;
import com.example.trabalhodaw2.Model.Lance;
import com.example.trabalhodaw2.Model.Leilao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanceService {

    @Autowired
    private LanceRepositorio lanceRepositorio;

    @Autowired
    private LeilaoRepositorio leilaoRepositorio;


    public List<Lance> BuscaTodos() {
        return lanceRepositorio.findAll();
    }

    public Lance BuscaLanceUnico(long id) {
        return lanceRepositorio.findById(id);
    }

    public Lance CadastrarLance(Lance lance) {
        List<Lance> list = BuscaLanceApartirDoIdLeilao(lance.getIdLeilao());
        Leilao leilao = leilaoRepositorio.findById((lance.getIdLeilao()));
        if(list.size() == 0){
            return lanceRepositorio.save(lance);
        }else{
           if(LanceNaoPodeAssumirValorMenorQueUtimoLance(lance.getValor(), list.get(list.size() -1).getValor())){
               if(MesmoParticipanteNaoPodeFazerDoisLancesSeguidos(lance.getNomeUsuario(), list.get(list.size() -1).getNomeUsuario())){
                   if(ValorLanceNaoPodeSerMenorQueValorMinimo(leilao.getValor(), lance.getValor())){
                       if(EfetuarLanceQuandoStatusLeilaoAberto(leilao.getStatus())){
                           return lanceRepositorio.save(lance);
                       }
                   }
               }
           }
        }
            return null;
    }

    public Lance AtulizarLance(long id, Lance newLance) {
        Lance lanceAntigo = lanceRepositorio.findById(id);
        Lance lance = lanceAntigo;
        lance.setNomeUsuario(newLance.getNomeUsuario());
        lance.setObjeto(newLance.getObjeto());
        lance.setValor(newLance.getValor());
        return lanceRepositorio.save(lance);
    }

    public HttpEntity<? extends Serializable> DeletarLeilao(long id) {
        Lance lance = lanceRepositorio.findById(id);
        if(lance != null){
            lanceRepositorio.deleteById(id);
            return new ResponseEntity<Lance>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Lance>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Lance> BuscaLanceApartirDoIdLeilao(long id){
        return lanceRepositorio.findLeilaoByIdLeilao(id);
    }

    public boolean ValorLanceNaoPodeSerMenorQueValorMinimo(double valorMinimo, double valorLance){
        if(valorLance > valorMinimo) {
            return true;
        }
        return false;
    }

    public boolean EfetuarLanceQuandoStatusLeilaoAberto(String status){
        if(status.equals("Aberto")){
            return true;
        }
        return false;
    }

    public boolean LanceNaoPodeAssumirValorMenorQueUtimoLance(double valorLance, double utimoLance){
        if(valorLance > utimoLance){
            return true;
        }
        return false;
    }

    public boolean MesmoParticipanteNaoPodeFazerDoisLancesSeguidos(String nomeAtual, String nomeAntigo){
        if(nomeAtual.equals(nomeAntigo)){
            return false;
        }
        return true;
    }

    public String RetornaNormeDoUtimoLanceApartirDoLeilao(long id){
        List<Lance> lance = BuscaLanceApartirDoIdLeilao(id);
         return lance.get(lance.size() -1).getNomeUsuario();
    }

}
