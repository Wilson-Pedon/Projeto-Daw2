package com.example.trabalhodaw2.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Lance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeUsuario;
    private String objeto;
    private Integer valor;
    private long idLeilao;

    public Lance(long id, String nomeUsuario, String objeto, Integer valor, long idLeilao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.objeto = objeto;
        this.valor = valor;
        this.idLeilao = idLeilao;
    }

    public Lance(){}

    public long getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(long idLeilao) {
        this.idLeilao = idLeilao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
