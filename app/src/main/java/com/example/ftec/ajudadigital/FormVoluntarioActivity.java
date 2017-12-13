package com.example.ftec.ajudadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ftec.ajudadigital.dao.CidadeDao;
import com.example.ftec.ajudadigital.dao.DoacaoDao;
import com.example.ftec.ajudadigital.dao.LoginDao;
import com.example.ftec.ajudadigital.dao.VoluntarioDao;
import com.example.ftec.ajudadigital.dao.dbDao;
import com.example.ftec.ajudadigital.modelo.Cidade;
import com.example.ftec.ajudadigital.modelo.Doacao;
import com.example.ftec.ajudadigital.modelo.Voluntario;

import java.util.List;

public class FormVoluntarioActivity extends AppCompatActivity {

    private FormVoluntarioHelper helper;
    private Spinner campoCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_voluntario);

        helper = new FormVoluntarioHelper(this);

        campoCidade = (Spinner) findViewById(R.id.formvoluntario_cidade);

        //Pegando o modelo passado como parâmetro

        //Intent intent = getIntent();
        //Voluntario voluntario = (Voluntario) intent.getSerializableExtra("voluntario");
        Voluntario voluntario = SessionRepository.getVoluntario();


        PreencheCidade();

        //Verificando se é novo registro ou esta vindo para edição
        if (voluntario != null){
            helper.preencheFormulario(voluntario);
            //Seta para o padrão
            selectSpinnerValue(campoCidade, voluntario.getCidade().toString());
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
                Voluntario voluntario = helper.pegaVoluntario();


                //Salvando Aluno no Banco de dados
                VoluntarioDao voluntariodao = new VoluntarioDao(this);
                CidadeDao cidadedao = new CidadeDao(this);
                LoginDao logindao = new LoginDao(this);

                //Verificando se o botão irá alterar ou irá incluir um registro novo
                if(voluntario.getId_voluntario() != null){

                    logindao.update(voluntario.getLogin());
                    cidadedao.update(voluntario.getCidade());
                    voluntariodao.update(voluntario);
                    finish();
                }
                else{
                    long id = logindao.insertRetornaId(voluntario.getLogin());
                    voluntario.setId_login((int) id);
                    voluntariodao.insert(voluntario);
                    helper.limpaFormulario();

                    Toast.makeText(this, "Registro Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                //voluntariodao.close();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void PreencheCidade(){

        //Busca do Banco
        CidadeDao dao = new CidadeDao(this);
        List<Cidade> recursos = dao.buscaCidade();
        dao.close();

        //Preenche um Adaptador conforme oque veio do banco
        ArrayAdapter<Cidade> adapdatorRecurso;
        adapdatorRecurso = new ArrayAdapter<Cidade>(this, android.R.layout.simple_spinner_dropdown_item, recursos);
        adapdatorRecurso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Seta o campo para o adaptador
        campoCidade.setAdapter(adapdatorRecurso);
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
