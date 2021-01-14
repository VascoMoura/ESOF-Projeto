package projectesof.esof.models;

import org.junit.jupiter.api.Test;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarefaTest {

    @Test
    public void adicionarEmpregado(){
        Tarefa tarefa = new Tarefa();
        tarefa.setName("Super tarefa");
        tarefa.setDias(0);
        tarefa.setHoras(2);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        Empregado empregado = new Empregado();
        empregado.setName("Nome Teste");
        empregado.setEmail("teste@hotmail.com");
        empregado.setCargo(Empregado.Cargo.AnalistaSenior);
        tarefa.adicionarEmpregado(empregado);



        assertEquals(empregado,tarefa.getEmpregado());

    }

}
