package com.example.trabalhodaw2.Service;

import com.example.trabalhodaw2.Dao.LeilaoRepositorio;
import com.example.trabalhodaw2.Model.Cliente;
import com.example.trabalhodaw2.Model.Lance;
import com.example.trabalhodaw2.Model.Leilao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LeilaoService {

    @Autowired
    private LeilaoRepositorio leilaoRepository;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private LanceService lanceService;

    @Autowired
    private ClienteService clienteService;

    public List<Leilao> BuscaTodos(){
        return leilaoRepository.findAll();
    }

    public Leilao BuscaLeilaoUnico(long id){
        return leilaoRepository.findById(id);
    }

    public Leilao CadastrarLeilao(Leilao leilao){
        leilao.setStatus("INATIVO");
        return leilaoRepository.save(leilao);
    }

    public HttpEntity<? extends Serializable> DeletarLeilao(long id){
        Leilao leilao = leilaoRepository.findById(id);
        if(leilao != null){
            leilaoRepository.deleteById(id);
            return new ResponseEntity<Leilao>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Leilao>(HttpStatus.NOT_FOUND);
        }
    }

    public Leilao AtulizarLeilao(long id, Leilao NovoLeilao) {
        Leilao leilaoAntigo = leilaoRepository.findById(id);
        Leilao leilao = leilaoAntigo;
        leilao.setNome(NovoLeilao.getNome());
        if (LeiloesExpiradosPodemSerFinalizados(NovoLeilao.getStatus(), NovoLeilao.getData())){
            leilao.setStatus("FINALIZADO");
            Cliente cliente = clienteService.BuscaEmailDoUtimoUsuarioDoLance(lanceService.RetornaNormeDoUtimoLanceApartirDoLeilao(leilao.getId()));
            mailConfig.EnviadorDeEmail(cliente.getEmail(),"Leilao do Wilson","O senhor(a) ganhou o leilao :" + NovoLeilao.getNome());
        }else {
            leilao.setStatus(NovoLeilao.getStatus());
        }
        leilao.setValor(NovoLeilao.getValor());
        leilao.setData(NovoLeilao.getData());
        return leilaoRepository.save(leilao);
    }

    public List<Leilao> BuscaLeilaoStatus(String status){
        return leilaoRepository.findLeilaoByStatus(status);
    }

    public List<Leilao> BuscaLeilaoStatus(){
        return leilaoRepository.findLeilaoByStatusAberto();
    }

    public void BuscaTodosLeiloesComDataExpirada() {
        List<Leilao> list = leilaoRepository.findAll();
        LocalDate localDate = LocalDate.now();
        for(Leilao l : list){
            if(l.getData().isBefore(localDate)){
                if(!l.getStatus().equals("FINALIZADO")){
                    l.setStatus("EXPIRADO");
                    leilaoRepository.save(l);
                }
            }
        }
    }

    public Boolean LeiloesExpiradosPodemSerFinalizados(String status, LocalDate data) {
        LocalDate dataAtual = LocalDate.now();
        if(!status.equals("INATIVO") && data.isBefore(dataAtual)) {
            return true;
        }
        return false;
    }



}
