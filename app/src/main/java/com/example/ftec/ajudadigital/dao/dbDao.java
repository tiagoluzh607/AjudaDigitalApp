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

public class dbDao extends SQLiteOpenHelper {
    protected Context contexto;

    public dbDao(Context context) {
        super(context, "AjudaDigital", null, 1);
        contexto = context;
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE login (id_login INTEGER PRIMARY KEY, email TEXT NOT NULL, senha TEXT, ativo bit);";
        sqLiteDatabase.execSQL(sql);

        String sql2 = "CREATE TABLE voluntario (id_voluntario INTEGER PRIMARY KEY, id_login INTEGER, id_cidade INTEGER, nome TEXT NOT NULL, cpf TEXT, endereco TEXT, telefone TEXT);";
        sqLiteDatabase.execSQL(sql2);

        String sql3 = "CREATE TABLE afinidade (id_afinidade INTEGER PRIMARY KEY, nome TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sql3);

        String sql4 = "CREATE TABLE vinculo_afinidade (id_vinculo_afinidade INTEGER PRIMARY KEY, id_afinidade INTEGER NOT NULL, id_voluntario INTEGER, id_entidade INTEGER, id_campanha INTEGER);";
        sqLiteDatabase.execSQL(sql4);

        String sql5 = "CREATE TABLE campanha (id_campanha INTEGER PRIMARY KEY, id_entidade INTEGER, nome TEXT NOT NULL, descricao text, data_inicial DATE, data_final DATE);";
        sqLiteDatabase.execSQL(sql5);

        String sql6 = "CREATE TABLE participa_campanha (id_participa_campanha INTEGER PRIMARY KEY, id_campanha INTEGER NOT NULL, id_voluntario INTEGER, id_entidade INTEGER ,ativo bit);";
        sqLiteDatabase.execSQL(sql6);

        String sql7 = "CREATE TABLE recurso (id_recurso INTEGER PRIMARY KEY, nome TEXT);";
        sqLiteDatabase.execSQL(sql7);


        String sql8 = "CREATE TABLE doacao (id_doacao INTEGER PRIMARY KEY, id_participa_campanha INTEGER, id_recurso TEXT NOT NULL, data DATE, quantidade INTEGER);";
        sqLiteDatabase.execSQL(sql8);

        String sql9 = "CREATE TABLE cidade (id_cidade INTEGER PRIMARY KEY, nome TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sql9);

        //Inserts Iniciais
        InsertAfinidade(sqLiteDatabase);
        InsertCampanha(sqLiteDatabase);
        InsertParticipaCampanha(sqLiteDatabase);
        InsertVinculoAfinidade(sqLiteDatabase);
        InsertRecurso(sqLiteDatabase);
        InsertCidade(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS login";
        sqLiteDatabase.execSQL(sql);

        String sql2 = "DROP TABLE IF EXISTS voluntario;";
        sqLiteDatabase.execSQL(sql2);

        String sql3 = "DROP TABLE IF EXISTS afinidade";
        sqLiteDatabase.execSQL(sql3);

        String sql4 = "DROP TABLE IF EXISTS vinculo_afinidade";
        sqLiteDatabase.execSQL(sql4);

        String sql5 = "DROP TABLE IF EXISTS campanha";
        sqLiteDatabase.execSQL(sql5);

        String sql6 = "DROP TABLE IF EXISTS participa_campanha";
        sqLiteDatabase.execSQL(sql6);

        String sql7 = "DROP TABLE IF EXISTS recurso";
        sqLiteDatabase.execSQL(sql7);


        String sql8 = "DROP TABLE IF EXISTS doacao";
        sqLiteDatabase.execSQL(sql8);

        String sql9 = "DROP TABLE IF EXISTS cidade";
        sqLiteDatabase.execSQL(sql9);


        onCreate(sqLiteDatabase);
    }

    public void InsertAfinidade(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Refugiados');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Crianças vítimas de abuso');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Pessoas em situação de rua');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Mulheres vítimas de violência');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Crianças desaparecidas');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Animais abandonados');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Crianças e adolescentes fora da escola');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Idosos');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Pessoas com deficiência');");
        sqLiteDatabase.execSQL("INSERT INTO afinidade(nome) values ('Direitos Humanos');");

    }

    public void InsertCampanha(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("INSERT INTO campanha(id_entidade, nome, descricao, data_inicial, data_final) values (1,'Campanha do Agasalho','Esta campanha é para ajudar quem precisa de roupas nesse inverno', '2017-01-01', '2018-02-25');");
        sqLiteDatabase.execSQL("INSERT INTO campanha(id_entidade, nome, descricao, data_inicial, data_final) values (1,'Todos Contra a Fome','Esta campanha é para ajudar Pessoas em situação de rua que estão passando fome', '2017-01-01', '2018-02-25');");
        sqLiteDatabase.execSQL("INSERT INTO campanha(id_entidade, nome, descricao, data_inicial, data_final) values (1,'Ajude a Maria da Penha','Esta campanha é para ajudar mulheres que sofrem violencia domestica', '2017-01-01', '2018-02-25');");
        sqLiteDatabase.execSQL("INSERT INTO campanha(id_entidade, nome, descricao, data_inicial, data_final) values (1,'Tecnicas de Bingo','Esta campanha é para ajudar na inclusão social de pessoas idosas', '2017-01-01', '2018-02-25');");
        sqLiteDatabase.execSQL("INSERT INTO campanha(id_entidade, nome, descricao, data_inicial, data_final) values (1,'Led Zeppelin','Esta campanha é uma aula de musica para refugiados', '2017-01-01', '2018-02-25');");
    }

    public void InsertParticipaCampanha(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (1, 1, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (2, 1, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (3, 2, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (4, 1, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (1, 2, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (2, 3, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (3, 3, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (5, 2, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (3, 4, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO participa_campanha(id_campanha, id_voluntario, id_entidade, ativo) values (1, 4, 0, 1);");
    }

    public void InsertVinculoAfinidade(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("INSERT INTO vinculo_afinidade(id_afinidade, id_voluntario, id_entidade, id_campanha) values (3, 0, 0, 1);");
        sqLiteDatabase.execSQL("INSERT INTO vinculo_afinidade(id_afinidade, id_voluntario, id_entidade, id_campanha) values (3, 0, 0, 2);");
        sqLiteDatabase.execSQL("INSERT INTO vinculo_afinidade(id_afinidade, id_voluntario, id_entidade, id_campanha) values (4, 0, 0, 3);");
        sqLiteDatabase.execSQL("INSERT INTO vinculo_afinidade(id_afinidade, id_voluntario, id_entidade, id_campanha) values (8, 0, 0, 4);");
        sqLiteDatabase.execSQL("INSERT INTO vinculo_afinidade(id_afinidade, id_voluntario, id_entidade, id_campanha) values (1, 0, 0, 5);");
        sqLiteDatabase.execSQL("INSERT INTO vinculo_afinidade(id_afinidade, id_voluntario, id_entidade, id_campanha) values (10, 0, 0, 5);");
    }

    public void InsertRecurso(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Arroz 1kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Arroz 2kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Arroz 5kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Feijao 1kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Feijao 2kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Feijao 5kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Oleo de soja 900ml');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Camisa manga curta');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Camisa manga longa');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Casaco');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Jaqueta');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Calça');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Bermuda');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Meias (par)');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Sapato (par)');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Tenis (par)');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Cobertor');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Papel Higienico (rolo)');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Sabonete');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Shampo');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Toalha');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Açucar Refinado 1kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Açucar Refinado 2kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Açucar Refinado 5kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Extrato de Tomate 140g');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Sal Refinado 1kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Sal Refinado 2kg');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Cafe Torrado 250g');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Macarrão Espaguete 500g');");
        sqLiteDatabase.execSQL("INSERT INTO recurso(nome) values ('Achocolatado em po 200g');");


    }

    public void InsertCidade(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("INSERT INTO cidade(nome) values ('Novo Hamburgo');");
        sqLiteDatabase.execSQL("INSERT INTO cidade(nome) values ('Campo Bom');");
        sqLiteDatabase.execSQL("INSERT INTO cidade(nome) values ('Sapiranga');");
        sqLiteDatabase.execSQL("INSERT INTO cidade(nome) values ('São Leopoldo');");
        sqLiteDatabase.execSQL("INSERT INTO cidade(nome) values ('Canoas');");
    }


}