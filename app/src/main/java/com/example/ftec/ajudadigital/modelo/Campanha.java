package com.example.ftec.ajudadigital.modelo;

/**
 * Created by tiago on 27/11/17.
 */

public class Campanha {

    private Long id_campanha;
    private int id_afinidade;
    private int id_entidade;
    private String nome;
    private String descricao;
    private String data_inicial;
    private String data_final;


    public Long getId_campanha() {
        return id_campanha;
    }

    public void setId_campanha(Long id_campanha) {
        this.id_campanha = id_campanha;
    }

    public int getId_afinidade() {
        return id_afinidade;
    }

    public void setId_afinidade(int id_afinidade) {
        this.id_afinidade = id_afinidade;
    }

    public int getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(String data_inicial) {
        this.data_inicial = data_inicial;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }
}
