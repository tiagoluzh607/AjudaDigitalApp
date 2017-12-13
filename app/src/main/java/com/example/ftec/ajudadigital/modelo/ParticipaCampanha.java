package com.example.ftec.ajudadigital.modelo;

import java.io.Serializable;
import java.io.StringBufferInputStream;

/**
 * Created by tiago on 27/11/17.
 */

public class ParticipaCampanha{


    private Long id_participa_campanha;
    private int id_campanha;
    private int id_voluntario;
    private int id_entidade;
    private int ativo;
    private Campanha campanha;

    public Long getId_participa_campanha() {
        return id_participa_campanha;
    }

    public void setId_participa_campanha(Long id_participa_campanha) {
        this.id_participa_campanha = id_participa_campanha;
    }

    public int getId_campanha() {
        return id_campanha;
    }

    public void setId_campanha(int id_campanha) {
        this.id_campanha = id_campanha;
    }

    public int getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(int id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    @Override
    public String toString(){
        return getCampanha().getNome();
    }


}
