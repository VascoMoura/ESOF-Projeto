package projectesof.esof.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.time.Duration;


@Getter
@Setter
@EqualsAndHashCode
public class Tarefa {

    private Long id;
    private String name;
    private Float valorEstimado;
    private Duration tempoEstimado;
    private Integer dias;
    private Integer horas;
    private Integer minutos;


    @EqualsAndHashCode.Exclude
    private Projeto projeto;
    private Empregado empregado;

    public void adicionarEmpregado(Empregado empregado){
        empregado.adicionarTarefa(this);
    }

}
