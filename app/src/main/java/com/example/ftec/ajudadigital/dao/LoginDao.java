package com.example.ftec.ajudadigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ftec.ajudadigital.modelo.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 06/11/17.
 */

public class LoginDao extends dbDao{

    public LoginDao(Context context) {
        super(context);
    }

    public void insert(Login login) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o insert
        ContentValues dados = new ContentValues();

        dados.put("email",login.getEmail());
        dados.put("senha",login.getSenha());
        dados.put("ativo", (login.getAtivo()));


        sqLiteDatabase.insert("login",null, dados);

    }

    public long insertRetornaId(Login login) {

        insert(login);

        String sql ="SELECT " +
                    "   *   " +
                    "FROM   " +
                    "   login " +
                    "WHERE id_login = (select" +
                                        " max(id_login) " +
                                      "from " +
                                         "login);";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabaseLeitura = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabaseLeitura.rawQuery(sql,null);

        List<Login> logins = new ArrayList<Login>();


        while(c.moveToNext()){
            Login loginobj = new Login();
            loginobj.setId_login(c.getLong(c.getColumnIndex("id_login")));
            loginobj.setEmail(c.getString(c.getColumnIndex("email")));
            loginobj.setAtivo(c.getInt(c.getColumnIndex("ativo")));
            loginobj.setSenha(c.getString(c.getColumnIndex("senha")));

            logins.add(loginobj);
        }
        //Fechar o Cursos para liberar a memória
        c.close();

        return logins.get(0).getId_login();

    }

    public List<Login> buscaLogin() {

        String sql = "SELECT * FROM login;";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Login> logins = new ArrayList<Login>();


        while(c.moveToNext()){
            Login login = new Login();
            login.setId_login(c.getLong(c.getColumnIndex("id_login")));
            login.setEmail(c.getString(c.getColumnIndex("email")));
            login.setAtivo(c.getInt(c.getColumnIndex("ativo")));
            login.setSenha(c.getString(c.getColumnIndex("senha")));

            logins.add(login);
        }
        //Fechar o Cursos para liberar a memória
        c.close();


        return logins;
    }

    public Login buscaLogin(Login loginobj){

        String sql = "SELECT " +
                "   * " +
                "FROM " +
                "   login WHERE email = '"+loginobj.getEmail()+"' AND senha = '"+ loginobj.getSenha() + "'";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Login> logins = new ArrayList<Login>();


        while(c.moveToNext()){
            Login login = new Login();
            login.setId_login(c.getLong(c.getColumnIndex("id_login")));
            login.setEmail(c.getString(c.getColumnIndex("email")));
            login.setAtivo(c.getInt(c.getColumnIndex("ativo")));
            login.setSenha(c.getString(c.getColumnIndex("senha")));

            logins.add(login);
        }
        //Fechar o Cursos para liberar a memória
        c.close();

        return logins.get(0);

    }

    public void delete(Login login) {

        //Pedindo o Banco de Dados pois nele tem uma funcao de insert
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] params = {login.getId_login().toString()};
        sqLiteDatabase.delete("login","id_login = ?",params);

    }

    public void update(Login login) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Abrindo o mapeamento dos dados para o update
        ContentValues dados = new ContentValues();

        dados.put("email",login.getEmail());
        dados.put("senha",login.getSenha());
        dados.put("ativo",login.getAtivo());


        String[] params = {login.getId_login().toString()};

        sqLiteDatabase.update("login",dados,"id_login = ?",params);
    }

    public boolean ExisteLogin(Login loginobj){

        String sql = "SELECT " +
                     "   * " +
                     "FROM " +
                     "   login WHERE email = '"+loginobj.getEmail()+"' AND senha = '"+ loginobj.getSenha() + "';";
        //Pedindo o Banco de dados para leitura somente
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //Metodo rawQuery retorna um Cursor
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Login> logins = new ArrayList<Login>();


        while(c.moveToNext()){
            Login login = new Login();
            login.setId_login(c.getLong(c.getColumnIndex("id_login")));
            login.setEmail(c.getString(c.getColumnIndex("email")));
            login.setAtivo(c.getInt(c.getColumnIndex("ativo")));
            login.setSenha(c.getString(c.getColumnIndex("senha")));

            logins.add(login);
        }
        //Fechar o Cursos para liberar a memória
        c.close();

        if(!logins.isEmpty()){
            return true;
        }

        return false;

    }

}