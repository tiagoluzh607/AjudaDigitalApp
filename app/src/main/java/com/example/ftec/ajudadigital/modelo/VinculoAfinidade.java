package com.example.ftec.ajudadigital.modelo;

/**
 * Created by tiago on 27/11/17.
 */

public class VinculoAfinidade {

    private Long id_vinculo_afinidade;
    private int id_afinidade;
    private int id_voluntario;
    private int id_entidade;
    private int id_campanha;

    public Long getId_vinculo_afinidade() {
        return id_vinculo_afinidade;
    }

    public void setId_vinculo_afinidade(Long id_vinculo_afinidade) {
        this.id_vinculo_afinidade = id_vinculo_afinidade;
    }

    public int getId_afinidade() {
        return id_afinidade;
    }

    public void setId_afinidade(int id_afinidade) {
        this.id_afinidade = id_afinidade;
    }

    public int getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(int id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public int getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    public int getId_campanha() {
        return id_campanha;
    }

    public void setId_campanha(int id_campanha) {
        this.id_campanha = id_campanha;
    }

}
