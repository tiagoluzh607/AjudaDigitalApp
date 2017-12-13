package com.example.ftec.ajudadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ftec.ajudadigital.dao.CampanhaDao;
import com.example.ftec.ajudadigital.dao.DoacaoDao;
import com.example.ftec.ajudadigital.modelo.Campanha;
import com.example.ftec.ajudadigital.modelo.Doacao;

import java.util.List;

public class ParticiparCampanhaActivity extends AppCompatActivity {

    private ListView listaDoacoes;
    private TextView campoTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participar_campanha);



        listaDoacoes = (ListView)findViewById(R.id.participar_campanha_lista_doacoes);
        campoTitulo = (TextView) findViewById(R.id.participar_campanha_titulo);

        carregaLista();

        registerForContextMenu(listaDoacoes);


        Button novaDoacao = (Button) findViewById(R.id.participar_campanha_botao_inserir);

        novaDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent exibirFormDoacao = new Intent(ParticiparCampanhaActivity.this, FormDoacaoActivity.class);

                startActivity(exibirFormDoacao);

            }
        });

    }


    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        //Alem de adicionar o camo menu na lista de contexto ainda coloca para um objeto deletar
        MenuItem editar = menu.add("Editar");
        MenuItem excluir = menu.add("Excluir");

        //Capturando click do item Editar
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                //Somente especificando o tipo para conseguir usar o metodo position que nos diz a posição
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                //Pegando o objeto da lista novamente e transformando em Doacao
                Doacao doacao = (Doacao) listaDoacoes.getItemAtPosition(info.position);

                //Exibir o formulario passândo um model como parametro
                Intent exibirFormulario = new Intent(ParticiparCampanhaActivity.this, FormDoacaoActivity.class);

                //Covertento modelo para um tipo que pode ser enviado e na string estamos passando uma chave de acesso
                exibirFormulario.putExtra("doacao",doacao);
                startActivity(exibirFormulario);





                return false;
            }
        });

        //Capturando click do item Excluir
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                //Somente especificando o tipo para conseguir usar o metodo position que nos diz a posição
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                //Pegando o objeto da lista novamente e transformando em Aluno
                Doacao doacao = (Doacao) listaDoacoes.getItemAtPosition(info.position);

                //Excluindo aluno do banco
                DoacaoDao dao = new DoacaoDao(ParticiparCampanhaActivity.this);
                dao.delete(doacao);
                dao.close();

                carregaLista();

                return false;
            }
        });



    }

    private void carregaLista() {

        DoacaoDao dao = new DoacaoDao(this);
        List<Doacao> doacoes = dao.buscaDoacoesdeCampanha(SessionRepository.getParticipaCampanha());
        dao.close();

        //o ArrayAdapter converterá o tipo String em um tipo View, pois listView só aceita Views
        ArrayAdapter<Doacao> adapter = new ArrayAdapter<Doacao>(this, android.R.layout.simple_list_item_1, doacoes);
        listaDoacoes.setAdapter(adapter);

        CampanhaDao campanhadao = new CampanhaDao(this);
        Campanha campanha = campanhadao.buscaCampanha(SessionRepository.getParticipaCampanha().getId_campanha());
        campoTitulo.setText(campanha.getNome());
    }



}
