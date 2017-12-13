package com.example.ftec.ajudadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ftec.ajudadigital.dao.AfinidadeDao;
import com.example.ftec.ajudadigital.dao.DoacaoDao;
import com.example.ftec.ajudadigital.dao.RecursoDao;
import com.example.ftec.ajudadigital.modelo.Afinidade;
import com.example.ftec.ajudadigital.modelo.Doacao;
import com.example.ftec.ajudadigital.modelo.Recurso;

import java.util.List;

public class FormDoacaoActivity extends AppCompatActivity {

    private FormDoacaoHelper helper;
    private Spinner campoRecurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_doacao);

        helper = new FormDoacaoHelper(this);

        campoRecurso = (Spinner) findViewById(R.id.formdoacao_recurso);

        //Pegando o modelo passado como parâmetro

        Intent intent = getIntent();
        Doacao doacao = (Doacao) intent.getSerializableExtra("doacao");




        PreencheRecurso();

        //Verificando se é novo registro ou esta vindo para edição
        if (doacao != null){
            helper.preencheFormulario(doacao);

            // Popula novamente o recurso pois o seriasible nao trabalha com objetos em cascata
            RecursoDao recursoDao = new RecursoDao(this);
            Recurso recurso = recursoDao.buscaRecurso(doacao.getId_recurso());
            doacao.setRecurso(recurso);


            //Seta para o padrão
            selectSpinnerValue(campoRecurso, recurso.toString());
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Usado para transformar um xml em uma view desenhavel na tela Inflater
        MenuInflater inflater = getMenuInflater();
        //Infla e coloca dentro do item de menu
        inflater.inflate(R.menu.menu_formulario, menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        //Verificando qual item de menu o usuario selecionou
        switch (item.getItemId()){

            case R.id.menu_formulario_ok:

                //Pegando a Doacao da Tela
                Doacao doacao = helper.pegaDoacao();

                Toast.makeText(FormDoacaoActivity.this, "Registro Salvo com Sucesso!", Toast.LENGTH_SHORT ).show();


                //Salvando Aluno no Banco de dados
                DoacaoDao dao = new DoacaoDao(this);

                //Verificando se o botão irá alterar ou irá incluir um registro novo
                if(doacao.getId_doacao() != null){

                    //Pega qual é o id do participa campanha que esta na sessão
                    int id_participa_campanha = Integer.parseInt(SessionRepository.getParticipaCampanha().getId_participa_campanha().toString());
                    doacao.setId_participa_campanha(id_participa_campanha);

                    dao.update(doacao);
                    finish();
                }
                else{
                    //Pega qual é o id do participa campanha que esta na sessão
                    int id_participa_campanha = Integer.parseInt(SessionRepository.getParticipaCampanha().getId_participa_campanha().toString());
                    doacao.setId_participa_campanha(id_participa_campanha);

                    dao.insert(doacao);
                    helper.limpaFormulario();
                }

                dao.close();

                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public void PreencheRecurso(){

        //Busca do Banco
        RecursoDao dao = new RecursoDao(this);
        List<Recurso> recursos = dao.buscaRecurso();
        dao.close();

        //Preenche um Adaptador conforme oque veio do banco
        ArrayAdapter<Recurso> adapdatorRecurso;
        adapdatorRecurso = new ArrayAdapter<Recurso>(this, android.R.layout.simple_spinner_dropdown_item, recursos);
        adapdatorRecurso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Seta o campo para o adaptador
        campoRecurso.setAdapter(adapdatorRecurso);
    }

    private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }

}
