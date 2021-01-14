package projectesof.design_patterns.builder;

import projectesof.esof.models.Empregado;
import projectesof.esof.models.Tarefa;

import java.util.ArrayList;

public class EmpregadoBuilder {
    private Empregado empregado;

    public EmpregadoBuilder(){
        this.empregado = new Empregado();
    }

    public EmpregadoBuilder setName(String name){
        this.empregado.setName(name);
        return this;
    }

    public Empregado build(){
        return this.empregado;
    }

    public EmpregadoBuilder adicionarTarefa(Tarefa tarefa){
        if(this.empregado.getTarefas() == null){ //se estiver vazio
            this.empregado.setTarefas(new ArrayList<>()); //cria o array list
        }
        this.empregado.getTarefas().add(tarefa);
        return this;
    }

    public static void main(String[] args) {
        Tarefa tarefa = new Tarefa();
        Empregado empregado=new EmpregadoBuilder().setName("teste").adicionarTarefa(tarefa).build();

    }

}
