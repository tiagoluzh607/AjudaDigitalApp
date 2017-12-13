package com.example.ftec.ajudadigital.modelo;

/**
 * Created by tiago on 20/11/17.
 */

public class Cidade {

    private Long id_cidade;
    private String nome;

    public Long getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(Long id_cidade) {
        this.id_cidade = id_cidade;
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
