package com.example.ftec.ajudadigital;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.ftec.ajudadigital.dao.AfinidadeDao;
import com.example.ftec.ajudadigital.modelo.Afinidade;

import java.util.List;

/**
 * Created by tiago on 03/12/17.
 */

public class AfinidadeHelper {

    private final Spinner campoAfinidade;
    private final ListView listaAfinidades;
    public AfinidadeActivity acti;
    private ArrayAdapter<Afinidade> afinidadesOpcoes;


    public AfinidadeHelper(AfinidadeActivity activity){

        campoAfinidade = (Spinner) activity.findViewById(R.id.formvoluntario_spinner_afinidade);
        listaAfinidades = (ListView) activity.findViewById(R.id.formvoluntario_lista_afinidades);

    }

    public Afinidade pegaAfinidade(){

        Afinidade afinidade = new Afinidade();
        afinidade = (Afinidade) campoAfinidade.getSelectedItem();
        return afinidade;
    }





}
