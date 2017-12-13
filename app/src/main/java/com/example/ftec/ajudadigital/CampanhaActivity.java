package com.example.ftec.ajudadigital;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ftec.ajudadigital.dao.CampanhaDao;
import com.example.ftec.ajudadigital.dao.ParticipaCampanhaDao;
import com.example.ftec.ajudadigital.modelo.Campanha;
import com.example.ftec.ajudadigital.modelo.ParticipaCampanha;
import com.example.ftec.ajudadigital.modelo.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class CampanhaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaCampanha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Acaba Implementação do Menu

        listaCampanha = (ListView) findViewById(R.id.content_campanha_lista_campanhas);

        //Diz que alista é um menu de Contexto (Aparece um menu ao segurar em cima)
        registerForContextMenu(listaCampanha);

        carregaLista();

        //Fica Escutando se algum item da lista foi clicado
        listaCampanha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {

               Intent exibirParticiparCampanha = new Intent(CampanhaActivity.this, ParticiparCampanhaActivity.class);
                //Covertento modelo para um tipo que pode ser enviado e na string estamos passando uma chave de acesso
                ParticipaCampanha participaCampanha = (ParticipaCampanha) listaCampanha.getItemAtPosition(position);
                SessionRepository.setParticipaCampanha(participaCampanha);
                //exibirParticiparCampanha.putExtra("participaCampanha", participaCampanha);
                startActivity(exibirParticiparCampanha);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        //Alem de adicionar o camo menu na lista de contexto ainda coloca para um objeto deletar
        MenuItem sair_campanha = menu.add("Sair da Campanha");

        //Capturando click do item
        sair_campanha.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {



                return false;
            }
        });



    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menuLateral_meus_dados) {
            // Handle the camera action
            Intent exibeFormVoluntario = new Intent(CampanhaActivity.this, FormVoluntarioActivity.class);
            startActivity(exibeFormVoluntario);


        } else if (id == R.id.menuLateral_campanhas) {

        } else if (id == R.id.menuLateral_afinidades) {

            Intent exibeAfinidade= new Intent(CampanhaActivity.this, AfinidadeActivity.class);
            startActivity(exibeAfinidade);

        } else if (id == R.id.menuLateral_sobre) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void carregaLista() {

        ParticipaCampanhaDao participaCampanhaDao = new ParticipaCampanhaDao(this);
        List<ParticipaCampanha> participaCampanhas = participaCampanhaDao.buscaParticipaCampanha_VinculoAfinidade(SessionRepository.getVoluntario());

        String[] alunos = {"Campanha do Agasalho","Campanha Led Zeppelin"};


        //o ArrayAdapter converterá o tipo String em um tipo View, pois listView só aceita Views
        ArrayAdapter<ParticipaCampanha> adapter = new ArrayAdapter<ParticipaCampanha>(this, android.R.layout.simple_list_item_1, participaCampanhas);
        listaCampanha.setAdapter(adapter);
    }
}
