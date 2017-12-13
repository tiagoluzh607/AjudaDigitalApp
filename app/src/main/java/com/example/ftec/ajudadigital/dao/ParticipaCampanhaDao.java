package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.ParticipaCampanha;
import com.example.ftec.ajudadigital.modelo.Voluntario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 27/11/17.
 */

public class ParticipaCampanhaDao extends dbDao {

    public ParticipaCampanhaDao(Context context) {
        super(context);
    }


    public void insert(ParticipaCampanha participa_campanha) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("id_campanha",participa_campanha.getId_campanha());
        dados.put("id_voluntario",participa_campanha.getId_voluntario());
        dados.put("id_entidade",participa_campanha.getId_entidade());
        dados.put("ativo", (participa_campanha.getAtivo()));


        sqLiteDatabase.insert("participa_campanha",null, dados);

    }

    public List<ParticipaCampanha> buscaParticipaCampanha() {

        String sql = "SELECT * FROM participa_campanha";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<ParticipaCampanha> participa_campanhas = new ArrayList<ParticipaCampanha>();


        while(c.moveToNext()){
            ParticipaCampanha participa_campanha = new ParticipaCampanha();
            participa_campanha.setId_participa_campanha(c.getLong(c.getColumnIndex("id_participa_campanha")));
            participa_campanha.setId_campanha(c.getInt(c.getColumnIndex("id_campanha")));
            participa_campanha.setId_voluntario(c.getInt(c.getColumnIndex("id_voluntario")));
            participa_campanha.setId_entidade(c.getInt(c.getColumnIndex("id_entidade")));
            participa_campanha.setAtivo(c.getInt(c.getColumnIndex("ativo")));

            CampanhaDao campanhadao = new CampanhaDao(super.contexto);
            int id_campanha = participa_campanha.getId_campanha();
            participa_campanha.setCampanha(campanhadao.buscaCampanha(id_campanha));
            participa_campanhas.add(participa_campanha);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return participa_campanhas;
    }

    public List<ParticipaCampanha> buscaParticipaCampanha_VinculoAfinidade(Voluntario voluntario) {

        //Query Faz o Vinvulo das afinidades do Voluntario com as Afinidades da Campanha

        String sql =    "SELECT " +
                        "   * " +
                        "FROM " +
                        "   participa_campanha as p " +
                        "WHERE " +
                        "   p.id_voluntario = " + voluntario.getId_voluntario() +
                        "   and " +
                        "   p.id_campanha in ( " +
                                                "select " +
                                                "   v1.id_campanha " +
                                                "from " +
                                                "   vinculo_afinidade as v " +
                                                "   inner join vinculo_afinidade as v1 on (v.id_afinidade = v1.id_afinidade and v.id_voluntario = "+voluntario.getId_voluntario()+") " +
                                                "where " +
                                                "   v1.id_campanha > 0 " +
                                            ")";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<ParticipaCampanha> participa_campanhas = new ArrayList<ParticipaCampanha>();


        while(c.moveToNext()){
            ParticipaCampanha participa_campanha = new ParticipaCampanha();
            participa_campanha.setId_participa_campanha(c.getLong(c.getColumnIndex("id_participa_campanha")));
            participa_campanha.setId_campanha(c.getInt(c.getColumnIndex("id_campanha")));
            participa_campanha.setId_voluntario(c.getInt(c.getColumnIndex("id_voluntario")));
            participa_campanha.setId_entidade(c.getInt(c.getColumnIndex("id_entidade")));
            participa_campanha.setAtivo(c.getInt(c.getColumnIndex("ativo")));

            CampanhaDao campanhadao = new CampanhaDao(super.contexto);
            int id_campanha = participa_campanha.getId_campanha();
            participa_campanha.setCampanha(campanhadao.buscaCampanha(id_campanha));
            participa_campanhas.add(participa_campanha);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return participa_campanhas;
    }

    public void delete(ParticipaCampanha participa_campanha) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {participa_campanha.getId_participa_campanha().toString()};
        sqLiteDatabase.delete("participa_campanha","id_participa_campanha = ?",params);

    }

    public void update(ParticipaCampanha participa_campanha) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("id_campanha",participa_campanha.getId_campanha());
        dados.put("id_voluntario",participa_campanha.getId_voluntario());
        dados.put("id_entidade",participa_campanha.getId_entidade());
        dados.put("ativo",participa_campanha.getAtivo());


        String[] params = {participa_campanha.getId_participa_campanha().toString()};

        sqLiteDatabase.update("participa_campanha",dados,"id_participa_campanha = ?",params);
    }

}