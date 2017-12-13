package com.example.ftec.ajudadigital;

import android.widget.EditText;

import com.example.ftec.ajudadigital.modelo.Login;

/**
 * Created by tiago on 28/11/17.
 */

public class LoginHelper {

    private final EditText campoEmail;
    private final EditText campoSenha;
    private Login login;


    public LoginHelper(LoginActivity activity){

        campoEmail = (EditText) activity.findViewById(R.id.login_email);
        campoSenha = (EditText) activity.findViewById(R.id.login_senha);
        login = new Login();

    }

    public Login pegaLogin(){

        login.setEmail(campoEmail.getText().toString());
        login.setSenha(campoSenha.getText().toString());

        return login;
    }

    public void preencheFormulario(Login login) {

        campoEmail.setText(login.getEmail());
        campoSenha.setText(login.getSenha());

        this.login = login;

    }

    public void limpaFormulario(){

        campoEmail.setText(null);
        campoSenha.setText(null);

    }



}
