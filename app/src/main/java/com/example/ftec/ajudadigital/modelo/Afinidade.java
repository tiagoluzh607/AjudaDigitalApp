package com.example.ftec.ajudadigital.modelo;

import java.io.Serializable;

/**
 * Created by tiago on 06/11/17.
 */

public class Afinidade implements Serializable{


    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
