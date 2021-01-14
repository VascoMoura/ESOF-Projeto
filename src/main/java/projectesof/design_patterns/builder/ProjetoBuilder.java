package projectesof.design_patterns.builder;
import projectesof.esof.models.Projeto;
import projectesof.esof.models.Tarefa;

import java.util.ArrayList;

public class ProjetoBuilder {
    private Projeto projeto;

    public ProjetoBuilder (){
        this.projeto = new Projeto();
    }

    public ProjetoBuilder setName(String name){
        this.projeto.setName(name);
        return this;
    }

    public Projeto build(){return this.projeto;}

    public ProjetoBuilder adicionarTarefa(Tarefa tarefa){
        if (this.projeto.getTarefas() == null){
            this.projeto.setTarefas(new ArrayList<>());
        }
        this.projeto.adicionarTarefa(tarefa);
        return this;
    }

    public static void main(String[] args){
        Tarefa tarefa = new Tarefa();
        Projeto projeto = new ProjetoBuilder().setName("teste do projeto").adicionarTarefa(tarefa).build();
    }
}
