package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Recurso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class RecursoDao extends dbDao {

    public RecursoDao(Context context) {
        super(context);
    }


    public void insert(Recurso recurso) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("nome",recurso.getNome());


        sqLiteDatabase.insert("recurso",null, dados);

    }

    public List<Recurso> buscaRecurso() {

        String sql = "SELECT * FROM recurso";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Recurso> doacoes = new ArrayList<Recurso>();


        while(c.moveToNext()){
            Recurso recurso = new Recurso();
            recurso.setId_recurso(c.getLong(c.getColumnIndex("id_recurso")));
            recurso.setNome(c.getString(c.getColumnIndex("nome")));


            doacoes.add(recurso);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return doacoes;
    }

    public Recurso buscaRecurso(int id) {

        String sql = "SELECT * FROM recurso WHERE id_recurso = "+id+";";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Recurso> doacoes = new ArrayList<Recurso>();


        while(c.moveToNext()){
            Recurso recurso = new Recurso();
            recurso.setId_recurso(c.getLong(c.getColumnIndex("id_recurso")));
            recurso.setNome(c.getString(c.getColumnIndex("nome")));


            doacoes.add(recurso);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return doacoes.get(0);
    }

    public void delete(Recurso recurso) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {recurso.getId_recurso().toString()};
        sqLiteDatabase.delete("recurso","id_recurso = ?",params);

    }

    public void update(Recurso recurso) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("nome",recurso.getNome());


        String[] params = {recurso.getId_recurso().toString()};

        sqLiteDatabase.update("recurso",dados,"id_recurso = ?",params);
    }

}
