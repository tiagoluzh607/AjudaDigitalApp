package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Cidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class CidadeDao extends dbDao {

    public CidadeDao(Context context) {
        super(context);
    }



    public void insert(Cidade cidade) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("nome",cidade.getNome());

        sqLiteDatabase.insert("cidade",null, dados);

    }

    public List<Cidade> buscaCidade() {

        String sql = "SELECT * FROM cidade";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Cidade> cidades = new ArrayList<Cidade>();


        while(c.moveToNext()){
            Cidade cidade = new Cidade();
            cidade.setId_cidade(c.getLong(c.getColumnIndex("id_cidade")));
            cidade.setNome(c.getString(c.getColumnIndex("nome")));

            cidades.add(cidade);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return cidades;
    }

    public Cidade buscaCidade(int id) {

        String sql = "SELECT * FROM cidade WHERE id_cidade = "+id;
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Cidade> cidades = new ArrayList<Cidade>();


        while(c.moveToNext()){
            Cidade cidade = new Cidade();
            cidade.setId_cidade(c.getLong(c.getColumnIndex("id_cidade")));
            cidade.setNome(c.getString(c.getColumnIndex("nome")));

            cidades.add(cidade);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return cidades.get(0);
    }

    public void delete(Cidade cidade) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {cidade.getId_cidade().toString()};
        sqLiteDatabase.delete("cidade","id_cidade = ?",params);

    }

    public void update(Cidade cidade) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("nome",cidade.getNome());


        String[] params = {cidade.getId_cidade().toString()};

        sqLiteDatabase.update("cidade",dados,"id_cidade = ?",params);
    }

}