package com.example.trabalhodaw2.Service;

import com.example.trabalhodaw2.Service.LeilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpiraDataAtrasada {


    @Autowired
    LeilaoService service;

    @Scheduled(fixedDelay = 2000)
    public void expiraDataAtrasada(){
        service.BuscaTodosLeiloesComDataExpirada();
    }


}
