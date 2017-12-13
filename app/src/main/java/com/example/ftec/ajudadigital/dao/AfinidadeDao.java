package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Afinidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class AfinidadeDao extends dbDao {


    public AfinidadeDao(Context context) {
        super(context);
    }


    public void insert(Afinidade afinidade) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("nome",afinidade.getNome());


        sqLiteDatabase.insert("afinidade",null, dados);

    }

    public List<Afinidade> buscaAfinidade() {

        String sql = "SELECT * FROM afinidade ORDER BY nome;";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Afinidade> afinidades = new ArrayList<Afinidade>();


        while(c.moveToNext()){
            Afinidade afinidade = new Afinidade();
            afinidade.setId(c.getLong(c.getColumnIndex("id_afinidade")));
            afinidade.setNome(c.getString(c.getColumnIndex("nome")));


            afinidades.add(afinidade);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return afinidades;
    }

    public void delete(Afinidade afinidade) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {afinidade.getId().toString()};
        sqLiteDatabase.delete("afinidade","id_afinidade = ?",params);

    }

    public void update(Afinidade afinidade) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("nome",afinidade.getNome());

        String[] params = {afinidade.getId().toString()};

        sqLiteDatabase.update("afinidade",dados,"id_afinidade = ?",params);
    }



}
