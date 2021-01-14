package projectesof.esof.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Empregado extends Utilizador{
    private Long id;
    private String email;
    private String name;
    private Cargo cargo;

    private List<Tarefa> tarefas=new ArrayList<>();

    public enum Cargo{
        DesenvolvedorJunior(10), AnalistaJunior(20), DesenvolvedorSenior(40), AnalistaSenior(80);
        public int valorHora;
        Cargo(int valorHora){
            this.valorHora=valorHora;
        }
    }

    public void adicionarTarefa(Tarefa tarefa){
        if(!this.tarefas.contains(tarefa)){
            this.tarefas.add(tarefa);
            tarefa.setEmpregado(this);
            long horas = tarefa.getTempoEstimado().toHours();
            tarefa.setValorEstimado(this.getCargo().valorHora * (float) horas);
        }
    }

}
