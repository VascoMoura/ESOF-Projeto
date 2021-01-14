package projectesof.esof.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import projectesof.esof.models.Empregado;
import projectesof.esof.models.Tarefa;
import projectesof.esof.repositories.EmpregadoRepository;
import projectesof.esof.repositories.TarefaRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaServiceImpl.class)
public class TarefaServiceImplTest {
    @Autowired
    private TarefaService tarefaService;

    @MockBean
    private TarefaRepository tarefaRepository;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void findAll() {
        when(tarefaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaService.findAll());
    }

    @Test
    void findById() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(new Tarefa()));

        assertTrue(tarefaService.findById(1L).isPresent());
        assertTrue(tarefaService.findById(2L).isEmpty());
    }

    @Test
    void createTarefa(){
        Tarefa tarefa = new Tarefa();
        tarefa.setName("Tarefa1");

        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(tarefaService.createTarefa(tarefa).isPresent());
    }

    @Test
    void adicionarEmpregado(){
        Empregado empregado = new Empregado();
        empregado.setName("Diogo");
        empregado.setEmail("diogo@email.com");
        empregado.setCargo(Empregado.Cargo.AnalistaSenior);

        Tarefa tarefa = new Tarefa();
        tarefa.setName("Tarefa2");
        tarefa.setDias(0);
        tarefa.setHoras(1);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.findByEmail(empregado.getEmail())).thenReturn(Optional.of(empregado));

        assertTrue(tarefaService.adicionarEmpregado(1L,empregado).isPresent());
        assertTrue(tarefaService.adicionarEmpregado(2L,empregado).isEmpty());


    }


}
