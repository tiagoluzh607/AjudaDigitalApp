package com.example.ftec.ajudadigital.modelo;

import java.io.Serializable;

/**
 * Created by tiago on 06/11/17.
 */

public class Doacao implements Serializable {


    private Long id_doacao;
    private int id_participa_campanha;
    private int id_recurso;
    private int quantidade;
    private String data;
    private transient Recurso recurso;


    public Long getId_doacao() {
        return id_doacao;
    }

    public void setId_doacao(Long id_doacao) {
        this.id_doacao = id_doacao;
    }

    public int getId_participa_campanha() {
        return id_participa_campanha;
    }

    public void setId_participa_campanha(int id_participa_campanha) {
        this.id_participa_campanha = id_participa_campanha;
    }

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    @Override
    public String toString() {

        return getData()+"  "+getRecurso().getNome()+"     "+getQuantidade()+"Un";

    }


}
