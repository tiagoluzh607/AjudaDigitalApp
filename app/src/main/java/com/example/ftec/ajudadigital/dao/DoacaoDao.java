package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Doacao;
import com.example.ftec.ajudadigital.modelo.ParticipaCampanha;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class DoacaoDao extends dbDao {

    public DoacaoDao(Context context) {
        super(context);
    }


    public void insert(Doacao doacao) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("id_participa_campanha",doacao.getId_participa_campanha());
        dados.put("id_recurso",doacao.getId_recurso());
        dados.put("data",doacao.getData());
        dados.put("quantidade",doacao.getQuantidade());


        sqLiteDatabase.insert("doacao",null, dados);

    }

    public List<Doacao> buscaDoacao() {

        String sql = "SELECT * FROM doacao";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Doacao> doacoes = new ArrayList<Doacao>();


        while(c.moveToNext()){
            Doacao doacao = new Doacao();
            doacao.setId_doacao(c.getLong(c.getColumnIndex("id_doacao")));
            doacao.setId_participa_campanha(c.getInt(c.getColumnIndex("id_participa_campanha")));
            doacao.setId_recurso(c.getInt(c.getColumnIndex("id_recurso")));
            doacao.setData(c.getString(c.getColumnIndex("data")));
            doacao.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));

            RecursoDao recursoDao = new RecursoDao(super.contexto);
            int id_recurso = doacao.getId_recurso();
            doacao.setRecurso(recursoDao.buscaRecurso(id_recurso));

            doacoes.add(doacao);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return doacoes;
    }

    public List<Doacao> buscaDoacoesdeCampanha(ParticipaCampanha participaCampanha) {

        String sql = "SELECT * FROM doacao WHERE id_participa_campanha = "+participaCampanha.getId_participa_campanha()+";";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Doacao> doacoes = new ArrayList<Doacao>();


        while(c.moveToNext()){
            Doacao doacao = new Doacao();
            doacao.setId_doacao(c.getLong(c.getColumnIndex("id_doacao")));
            doacao.setId_participa_campanha(c.getInt(c.getColumnIndex("id_participa_campanha")));
            doacao.setId_recurso(c.getInt(c.getColumnIndex("id_recurso")));
            doacao.setData(c.getString(c.getColumnIndex("data")));
            doacao.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));

            RecursoDao recursoDao = new RecursoDao(super.contexto);
            int id_recurso = doacao.getId_recurso();
            doacao.setRecurso(recursoDao.buscaRecurso(id_recurso));

            doacoes.add(doacao);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return doacoes;
    }

    public void delete(Doacao doacao) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {doacao.getId_doacao().toString()};
        sqLiteDatabase.delete("doacao","id_doacao = ?",params);

    }

    public void update(Doacao doacao) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("id_participa_campanha",doacao.getId_participa_campanha());
        dados.put("id_recurso",doacao.getId_recurso());
        dados.put("data",doacao.getData());
        dados.put("quantidade",doacao.getQuantidade());


        String[] params = {doacao.getId_doacao().toString()};

        sqLiteDatabase.update("doacao",dados,"id_doacao = ?",params);
    }

}
