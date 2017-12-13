package com.example.ftec.ajudadigital;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.ftec.ajudadigital.modelo.Doacao;
import com.example.ftec.ajudadigital.modelo.Recurso;

/**
 * Created by tiago on 06/11/17.
 */

public class FormDoacaoHelper {


    private final EditText campoData;
    private final EditText campoQuantidade;
    private final Spinner campoRecurso;
    private Doacao doacao;

    public FormDoacaoHelper(FormDoacaoActivity activity){

        doacao = new Doacao();
        campoRecurso = (Spinner) activity.findViewById(R.id.formdoacao_recurso);
        campoData = (EditText) activity.findViewById(R.id.formdoacao_data);
        campoQuantidade = (EditText)activity.findViewById(R.id.formdoacao_quantidade);

    }

    public Doacao pegaDoacao(){


        doacao.setData(campoData.getText().toString());
        doacao.setQuantidade( Integer.parseInt(campoQuantidade.getText().toString()));
        Recurso recurso = (Recurso) campoRecurso.getSelectedItem();
        int id = Integer.parseInt(recurso.getId_recurso().toString());
        doacao.setId_recurso(id);

        return doacao;
    }

    public void preencheFormulario(Doacao doacao) {


        campoData.setText(doacao.getData());
        campoQuantidade.setText(doacao.getQuantidade()+"");

        this.doacao = doacao;

    }

    public void limpaFormulario(){


        campoData.setText(null);
        campoQuantidade.setText(null);

    }


}
