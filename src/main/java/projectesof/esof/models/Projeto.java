package projectesof.esof.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;




@Getter
@Setter
@EqualsAndHashCode
public class Projeto {
    private Long id;
    private String name;
    private Float valorEstimado;
    private Duration tempoEstimado;
    //private LocalTime tempo;

    private List<Tarefa> tarefas=new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa){
        if(!this.tarefas.contains(tarefa)){
            this.tarefas.add(tarefa);
            tarefa.setProjeto(this);
            //this.tempoEstimado = this.tempoEstimado.plus(tarefa.getTempoEstimado());

        }
    }

    public void custoProjeto(){
        Duration tempo = Duration.ofDays(0);
        Float valor = 0F;
        for (Tarefa tarefa : tarefas)
        {
            tempo = tempo.plus(tarefa.getTempoEstimado());
            if(tarefa.getEmpregado() != null){
                valor = valor + tarefa.getValorEstimado();
            }
        }
        this.valorEstimado = valor;
        this.tempoEstimado = tempo;
    }


}
