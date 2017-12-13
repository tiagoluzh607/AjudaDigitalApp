package com.example.ftec.ajudadigital;

import com.example.ftec.ajudadigital.modelo.ParticipaCampanha;
import com.example.ftec.ajudadigital.modelo.Voluntario;

/**
 * Created by tiago on 29/11/17.
 */

public final class SessionRepository {

    static Voluntario voluntario;
    static ParticipaCampanha participaCampanha;



    public SessionRepository(){

    }


    public static Voluntario getVoluntario() {
        return voluntario;
    }

    public static void setVoluntario(Voluntario voluntario) {
        SessionRepository.voluntario = voluntario;
    }

    public static ParticipaCampanha getParticipaCampanha() {
        return participaCampanha;
    }

    public static void setParticipaCampanha(ParticipaCampanha participaCampanha) {
        SessionRepository.participaCampanha = participaCampanha;
    }
}
