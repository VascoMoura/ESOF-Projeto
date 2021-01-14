package projectesof.esof.models;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjetoTest {

    @Test
    public void adicionarTarefa() {
        Projeto projeto = new Projeto();
        projeto.setName("PROJETO");


        Tarefa tarefa = new Tarefa();
        tarefa.setName("tarefa1");
        tarefa.setDias(0);
        tarefa.setHoras(2);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        projeto.adicionarTarefa(tarefa);
        assertEquals(1,projeto.getTarefas().size());

    }

    @Test
    public void custoProjeto() {
        Projeto projeto = new Projeto();
        projeto.setName("PROJETO");

        Tarefa tarefa = new Tarefa();
        tarefa.setName("tarefa1");
        tarefa.setDias(0);
        tarefa.setHoras(2);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        Empregado empregado = new Empregado();
        empregado.setEmail("ok@hotmil.com");
        empregado.setName("ok");
        empregado.setCargo(Empregado.Cargo.AnalistaSenior);

        projeto.adicionarTarefa(tarefa);
        tarefa.adicionarEmpregado(empregado);

        projeto.custoProjeto();

        assertNotNull(projeto.getTempoEstimado());
        assertNotNull(projeto.getValorEstimado());
    }
}
