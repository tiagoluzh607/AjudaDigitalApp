package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Afinidade;
import com.example.ftec.ajudadigital.modelo.VinculoAfinidade;
import com.example.ftec.ajudadigital.modelo.Voluntario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class VinculoAfinidadeDao extends dbDao {

    public VinculoAfinidadeDao(Context context) {
        super(context);
    }


    public void insert(VinculoAfinidade vinculoafinidade) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("id_afinidade",vinculoafinidade.getId_afinidade());
        dados.put("id_voluntario",vinculoafinidade.getId_voluntario());
        dados.put("id_entidade", (vinculoafinidade.getId_entidade()));
        dados.put("id_campanha", (vinculoafinidade.getId_campanha()));


        sqLiteDatabase.insert("vinculo_afinidade",null, dados);

    }

    public List<VinculoAfinidade> buscaVinculoAfinidade() {

        String sql = "SELECT * FROM vinculo_afinidade";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<VinculoAfinidade> vinculoafinidades = new ArrayList<VinculoAfinidade>();


        while(c.moveToNext()){
            VinculoAfinidade vinculoafinidade = new VinculoAfinidade();
            vinculoafinidade.setId_vinculo_afinidade(c.getLong(c.getColumnIndex("id_vinculo_afinidade")));
            vinculoafinidade.setId_voluntario(c.getInt(c.getColumnIndex("id_voluntario")));
            vinculoafinidade.setId_entidade(c.getInt(c.getColumnIndex("id_entidade")));
            vinculoafinidade.setId_campanha(c.getInt(c.getColumnIndex("id_campanha")));

            vinculoafinidades.add(vinculoafinidade);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return vinculoafinidades;
    }

    public void delete(VinculoAfinidade vinculoafinidade) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {vinculoafinidade.getId_vinculo_afinidade().toString()};
        sqLiteDatabase.delete("vinculo_afinidade","id_vinculo_afinidade = ?",params);

    }

    public void delete(Afinidade afinidade, Voluntario voluntario) {

        //Pedindo o Banco de Dados pois nele tem uma funcao do delete
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {afinidade.getId().toString(), voluntario.getId_voluntario().toString()};
        sqLiteDatabase.delete("vinculo_afinidade","id_afinidade = ? AND id_voluntario = ?",params);

    }


    public void update(VinculoAfinidade vinculoafinidade) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("id_afinidade",vinculoafinidade.getId_afinidade());
        dados.put("id_voluntario",vinculoafinidade.getId_voluntario());
        dados.put("id_entidade",vinculoafinidade.getId_entidade());
        dados.put("id_campanha",vinculoafinidade.getId_campanha());


        String[] params = {vinculoafinidade.getId_vinculo_afinidade().toString()};

        sqLiteDatabase.update("vinculo_afinidade",dados,"id_vinculo_afinidade = ?",params);
    }

    public List<Afinidade> buscaAfinidades(Voluntario voluntario){

        String sql = "SELECT " +
                     "   a.id_afinidade, " +
                     "   a.nome " +
                     "FROM " +
                     "   vinculo_afinidade    as v " +
                     "   inner join afinidade as a on (v.id_afinidade = a.id_afinidade) " +
                     "WHERE " +
                     "   v.id_voluntario = "+ voluntario.getId_voluntario() +" "+
                     "ORDER BY " +
                     "   a.nome;";
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

}