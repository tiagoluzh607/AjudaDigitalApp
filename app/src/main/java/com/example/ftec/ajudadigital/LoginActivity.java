package com.example.ftec.ajudadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ftec.ajudadigital.dao.CidadeDao;
import com.example.ftec.ajudadigital.dao.LoginDao;
import com.example.ftec.ajudadigital.dao.VoluntarioDao;
import com.example.ftec.ajudadigital.dao.dbDao;
import com.example.ftec.ajudadigital.modelo.Login;
import com.example.ftec.ajudadigital.modelo.Voluntario;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Cria e-ou Atuliza a Estrutura de banco de dados
        dbDao db = new dbDao(this);

        helper = new LoginHelper(this);


        //Botao Entrar
        Button botao_entrar = (Button) findViewById(R.id.login_botao_entrar);
        botao_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pega o Login da tela
                Login login = helper.pegaLogin();

                LoginDao logindao = new LoginDao(LoginActivity.this);

                if(logindao.ExisteLogin(login)){

                    //Salva voluntario na sessão

                    Login loginObj = logindao.buscaLogin(login);
                    logindao.close();

                    VoluntarioDao voluntariodao = new VoluntarioDao(LoginActivity.this);

                    //Pega dados do Voluntario para Salvar na sessão Login e Cidade
                    Voluntario voluntario = voluntariodao.buscaVoluntarioporLogin(loginObj);
                    voluntario.setLogin(loginObj);
                    voluntariodao.close();

                    //Cidade
                    CidadeDao cidadedao = new CidadeDao(LoginActivity.this);
                    voluntario.setCidade(cidadedao.buscaCidade(voluntario.getId_cidade()));

                    SessionRepository.setVoluntario(voluntario);

                    //Abre o Menu Principal
                    Intent exibeCampanha = new Intent(LoginActivity.this, CampanhaActivity.class);
                    startActivity(exibeCampanha);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Usuario ou senha Incorretos", Toast.LENGTH_SHORT).show();
                }



            }
        });

        //Botao Cadastrar
        Button botao_cadastrar = (Button) findViewById(R.id.login_botao_cadastrar);
        botao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SessionRepository.setVoluntario(null);

                Intent exibeFormVoluntario = new Intent(LoginActivity.this, FormVoluntarioActivity.class);
                startActivity(exibeFormVoluntario);

                int teste = 0;
            }
        });



    }
}
