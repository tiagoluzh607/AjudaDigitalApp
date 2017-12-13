package com.example.ftec.ajudadigital.modelo;

import java.io.Serializable;

/**
 * Created by tiago on 04/12/17.
 */

public class Recurso implements Serializable {

    private Long id_recurso;
    private String nome;

    public Long getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(Long id_recurso) {
        this.id_recurso = id_recurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){

        return getNome();
    }


}
