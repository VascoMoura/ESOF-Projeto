package projectesof.design_patterns.builder;
import projectesof.esof.models.Empregado;
import projectesof.esof.models.Tarefa;

import java.time.Duration;


public class TarefaBuilder {
    private Tarefa tarefa;

    public  TarefaBuilder(){
        this.tarefa = new Tarefa();
    }

    public TarefaBuilder setName(String name){
        this.tarefa.setName(name);
        return this;
    }

    public TarefaBuilder setTempo(Duration tempo){
        this.tarefa.setTempoEstimado(tempo);
        return this;
    }

    public Tarefa build(){
        return this.tarefa;
    }

    public TarefaBuilder adicionarEmpregado(Empregado empregado) {

        /*if (this.tarefa.getEmpregado() == null){
            this.tarefa.setEmpregado(new ArrayList<>());
        }*/

        this.tarefa.adicionarEmpregado(empregado);
        return this;
    }

    public static void main(String[] args){
        Empregado empregado = new Empregado();
        Tarefa tarefa = new TarefaBuilder().setName("teste das tarefas").setTempo(Duration.ofHours(1L)).adicionarEmpregado(empregado).build();

    }
}
