package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Campanha;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 27/11/17.
 */

public class CampanhaDao extends dbDao {

    public CampanhaDao(Context context) {
        super(context);
    }


    public void insert(Campanha campanha) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("id_afinidade",campanha.getId_afinidade());
        dados.put("id_entidade",campanha.getId_entidade());
        dados.put("nome", (campanha.getNome()));
        dados.put("descricao", (campanha.getDescricao()));
        dados.put("data_inicial", (campanha.getData_inicial()));
        dados.put("data_final", (campanha.getData_final()));


        sqLiteDatabase.insert("campanha",null, dados);

    }

    public List<Campanha> buscaCampanha() {

        String sql = "SELECT * FROM campanha";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Campanha> campanhas = new ArrayList<Campanha>();


        while(c.moveToNext()){
            Campanha campanha = new Campanha();
            campanha.setId_campanha(c.getLong(c.getColumnIndex("id_campanha")));
            campanha.setId_entidade(c.getInt(c.getColumnIndex("id_entidade")));
            campanha.setNome(c.getString(c.getColumnIndex("nome")));
            campanha.setDescricao(c.getString(c.getColumnIndex("descricao")));
            campanha.setData_inicial(c.getString(c.getColumnIndex("data_inicial")));
            campanha.setData_final(c.getString(c.getColumnIndex("data_final")));

            campanhas.add(campanha);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return campanhas;
    }

    public Campanha buscaCampanha(int id) {

        String sql = "SELECT * FROM campanha WHERE id_campanha = "+id+" ;";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Campanha> campanhas = new ArrayList<Campanha>();


        while(c.moveToNext()){
            Campanha campanha = new Campanha();
            campanha.setId_campanha(c.getLong(c.getColumnIndex("id_campanha")));
            campanha.setId_entidade(c.getInt(c.getColumnIndex("id_entidade")));
            campanha.setNome(c.getString(c.getColumnIndex("nome")));
            campanha.setDescricao(c.getString(c.getColumnIndex("descricao")));
            campanha.setData_inicial(c.getString(c.getColumnIndex("data_inicial")));
            campanha.setData_final(c.getString(c.getColumnIndex("data_final")));

            campanhas.add(campanha);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return campanhas.get(0);
    }

    public void delete(Campanha campanha) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {campanha.getId_campanha().toString()};
        sqLiteDatabase.delete("campanha","id_campanha = ?",params);

    }

    public void update(Campanha campanha) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("id_entidade",campanha.getId_entidade());
        dados.put("nome",campanha.getNome());
        dados.put("descricao",campanha.getDescricao());
        dados.put("data_inicial",campanha.getData_inicial());
        dados.put("data_final",campanha.getData_final());


        String[] params = {campanha.getId_campanha().toString()};

        sqLiteDatabase.update("campanha",dados,"id_campanha = ?",params);
    }

}