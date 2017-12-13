package com.example.ftec.ajudadigital;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.ftec.ajudadigital.modelo.Cidade;
import com.example.ftec.ajudadigital.modelo.Login;
import com.example.ftec.ajudadigital.modelo.Voluntario;

/**
 * Created by tiago on 27/11/17.
 */

public class FormVoluntarioHelper {


    private final EditText campoEmail;
    private final EditText campoSenha;

    private final EditText campoNome;
    private final EditText campoCpf;
    private final EditText campoEndereco;
    private Spinner campoCidade;
    private final EditText campoTelefone;

    private Voluntario voluntario;

    public FormVoluntarioHelper(FormVoluntarioActivity activity){

        voluntario = new Voluntario();
        campoEmail = (EditText) activity.findViewById(R.id.formvoluntario_email);
        campoSenha = (EditText) activity.findViewById(R.id.formvoluntario_senha);
        campoNome = (EditText) activity.findViewById(R.id.formvoluntario_nome);
        campoCpf = (EditText) activity.findViewById(R.id.formvoluntario_cpf);
        campoEndereco = (EditText) activity.findViewById(R.id.formvoluntario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formvoluntario_telefone);
        campoCidade = (Spinner)  activity.findViewById(R.id.formvoluntario_cidade);
    }

    public Voluntario pegaVoluntario(){

        voluntario.getLogin().setEmail(campoEmail.getText().toString());
        voluntario.getLogin().setSenha(campoSenha.getText().toString());

        voluntario.setNome(campoNome.getText().toString());
        voluntario.setCpf(campoCpf.getText().toString());
        voluntario.setEndereco(campoEndereco.getText().toString());
        voluntario.setTelefone(campoTelefone.getText().toString());

        Cidade cidade = (Cidade) campoCidade.getSelectedItem();
        int id_cidade = Integer.parseInt(cidade.getId_cidade().toString());
        voluntario.setId_cidade(id_cidade);

        return voluntario;
    }

    public void preencheFormulario(Voluntario voluntario) {


        campoEmail.setText(voluntario.getLogin().getEmail());
        campoSenha.setText(voluntario.getLogin().getSenha());
        campoNome.setText(voluntario.getNome());
        campoCpf.setText(voluntario.getCpf());
        campoEndereco.setText(voluntario.getEndereco());
        campoTelefone.setText(voluntario.getTelefone());

        this.voluntario = voluntario;

    }

    public void limpaFormulario(){

        campoEmail.setText(null);
        campoSenha.setText(null);
        campoNome.setText(null);
        campoCpf.setText(null);
        campoEndereco.setText(null);
        campoTelefone.setText(null);


    }


}
