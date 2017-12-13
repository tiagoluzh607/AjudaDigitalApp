package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Login;
import com.example.ftec.ajudadigital.modelo.Voluntario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class VoluntarioDao extends dbDao {

    public VoluntarioDao(Context context) {
        super(context);
    }




    public void insert(Voluntario voluntario) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("id_login",voluntario.getId_login());
        dados.put("id_cidade",voluntario.getId_cidade());
        dados.put("nome",voluntario.getNome());
        dados.put("cpf", (voluntario.getCpf()));
        dados.put("endereco", (voluntario.getEndereco()));
        dados.put("telefone", (voluntario.getTelefone()));


        sqLiteDatabase.insert("voluntario",null, dados);

    }

    public List<Voluntario> buscaVoluntario() {

        String sql = "SELECT * FROM voluntario;";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Voluntario> voluntarios = new ArrayList<Voluntario>();


        while(c.moveToNext()){
            Voluntario voluntario = new Voluntario();
            voluntario.setId_voluntario(c.getLong(c.getColumnIndex("id_voluntario")));
            voluntario.setId_login(c.getInt(c.getColumnIndex("id_login")));
            voluntario.setId_cidade(c.getInt(c.getColumnIndex("id_cidade")));
            voluntario.setNome(c.getString(c.getColumnIndex("nome")));
            voluntario.setCpf(c.getString(c.getColumnIndex("cpf")));
            voluntario.setEndereco(c.getString(c.getColumnIndex("endereco")));
            voluntario.setTelefone(c.getString(c.getColumnIndex("telefone")));

            voluntarios.add(voluntario);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return voluntarios;
    }

    public Voluntario buscaVoluntarioporLogin(Login login) {

        String sql = "SELECT * FROM voluntario WHERE id_login = "+login.getId_login()+";";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Voluntario> voluntarios = new ArrayList<Voluntario>();


        while(c.moveToNext()){
            Voluntario voluntario = new Voluntario();
            voluntario.setId_voluntario(c.getLong(c.getColumnIndex("id_voluntario")));
            voluntario.setId_login(c.getInt(c.getColumnIndex("id_login")));
            voluntario.setId_cidade(c.getInt(c.getColumnIndex("id_cidade")));
            voluntario.setNome(c.getString(c.getColumnIndex("nome")));
            voluntario.setCpf(c.getString(c.getColumnIndex("cpf")));
            voluntario.setEndereco(c.getString(c.getColumnIndex("endereco")));
            voluntario.setTelefone(c.getString(c.getColumnIndex("telefone")));

            voluntarios.add(voluntario);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return voluntarios.get(0);
    }

    public void delete(Voluntario voluntario) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {voluntario.getId_voluntario().toString()};
        sqLiteDatabase.delete("voluntario","id_voluntario = ?",params);

    }

    public void update(Voluntario voluntario) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("id_login",voluntario.getId_login());
        dados.put("id_cidade",voluntario.getId_cidade());
        dados.put("nome",voluntario.getNome());
        dados.put("cpf",voluntario.getCpf());
        dados.put("endereco",voluntario.getEndereco());
        dados.put("telefone",voluntario.getTelefone());


        String[] params = {voluntario.getId_voluntario().toString()};

        sqLiteDatabase.update("voluntario",dados,"id_voluntario = ?",params);
    }

}