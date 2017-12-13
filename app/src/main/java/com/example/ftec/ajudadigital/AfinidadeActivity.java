package com.example.ftec.ajudadigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ftec.ajudadigital.dao.AfinidadeDao;
import com.example.ftec.ajudadigital.dao.VinculoAfinidadeDao;
import com.example.ftec.ajudadigital.modelo.Afinidade;
import com.example.ftec.ajudadigital.modelo.VinculoAfinidade;

import java.util.List;

public class AfinidadeActivity extends AppCompatActivity {

    private AfinidadeHelper helper;
    private Spinner campoAfinidade;
    private ArrayAdapter<Afinidade> afinidadesOpcoes;
    private ListView listaAfinidades;
    private Button botao_inserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinidade);



        helper = new AfinidadeHelper(this);

        campoAfinidade = (Spinner) findViewById(R.id.formvoluntario_spinner_afinidade);
        listaAfinidades =(ListView) findViewById(R.id.formvoluntario_lista_afinidades);
        botao_inserir = (Button) findViewById(R.id.formvoluntario_inserir);

        //Diz que a lista é um menu de Contexto (Aparece um menu ao segurar em cima)
        registerForContextMenu(listaAfinidades);

        PreencheAfinidades();
        CarregaLista();

        botao_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AfinidadeActivity.this, ""+helper.pegaAfinidade().getNome(), Toast.LENGTH_SHORT).show();

                VinculoAfinidade vinculo = new VinculoAfinidade();

                //Pegando o Voluntario Logado para setar o vinculo
                long id_voluntario = SessionRepository.getVoluntario().getId_voluntario();
                vinculo.setId_voluntario((int) id_voluntario);

                //Pegando a Afinidade Escolida para setar o vinculo
                long id_afinidade = helper.pegaAfinidade().getId();
                vinculo.setId_afinidade((int) id_afinidade);

                //Salvando vinculo no banco de dados
                VinculoAfinidadeDao vinculodao = new VinculoAfinidadeDao(AfinidadeActivity.this);
                vinculodao.insert(vinculo);
                vinculodao.close();

                //Carrega a lista
                CarregaLista();
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        //Alem de adicionar o camo menu na lista de contexto ainda coloca para um objeto deletar
        MenuItem deletar = menu.add("Deletar");

        //Capturando click do item
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                //Somente especificando o tipo para conseguir usar o metodo position que nos diz a posição
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                //Pegando o objeto da lista novamente e transformando em Aluno
                Afinidade afinidade = (Afinidade) listaAfinidades.getItemAtPosition(info.position);

                //Excluindo aluno do banco
                VinculoAfinidadeDao vinculodao = new VinculoAfinidadeDao(AfinidadeActivity.this);
                vinculodao.delete(afinidade, SessionRepository.getVoluntario());
                vinculodao.close();

                CarregaLista();

                return false;
            }
        });



    }


    public void PreencheAfinidades(){

        //Busca do Banco
        AfinidadeDao dao = new AfinidadeDao(this);
        List<Afinidade> afinidades = dao.buscaAfinidade();
        dao.close();

        //Preenche um Adaptador conforme oque veio do banco
        afinidadesOpcoes = new ArrayAdapter<Afinidade>(this, android.R.layout.simple_spinner_dropdown_item, afinidades);
        afinidadesOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Seta o campo para o adaptador
        campoAfinidade.setAdapter(afinidadesOpcoes);
    }

    public void CarregaLista(){
        VinculoAfinidadeDao vinculodao = new VinculoAfinidadeDao(this);
        List<Afinidade> afinidades = vinculodao.buscaAfinidades(SessionRepository.getVoluntario());
        vinculodao.close();

        //o ArrayAdapter converterá o tipo String em um tipo View, pois listView só aceita Views
        ArrayAdapter<Afinidade> adapter = new ArrayAdapter<Afinidade>(this, android.R.layout.simple_list_item_1, afinidades );
        listaAfinidades.setAdapter(adapter);
    }

}
