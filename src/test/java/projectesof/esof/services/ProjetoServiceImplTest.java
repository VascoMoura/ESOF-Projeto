package projectesof.esof.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import projectesof.esof.models.Empregado;
import projectesof.esof.models.Projeto;
import projectesof.esof.models.Tarefa;
import projectesof.esof.repositories.ProjetoRepository;
import projectesof.esof.repositories.TarefaRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
public class ProjetoServiceImplTest {
    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaRepository tarefaRepository;

    @Test
    void findAll() {
        when(projetoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertTrue(projetoService.findById(2L).isEmpty());
    }

    @Test
    void createProjeto(){
        Projeto projeto = new Projeto();
        projeto.setName("NovoooProjeto");

        when(projetoRepository.save(projeto)).thenReturn(projeto);
        assertTrue(projetoService.createProjeto(projeto).isPresent());

        when(projetoRepository.findByName(projeto.getName())).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.createProjeto(projeto).isEmpty());
    }

    @Test
    void adicionarTarefa(){
        Tarefa tarefa = new Tarefa();
        tarefa.setName("tarrrr2");

        Projeto projeto = new Projeto();
        projeto.setName("NovoooProjeto");

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        assertTrue(projetoService.adicionarTarefa(1L,tarefa).isPresent());
        assertTrue(projetoService.adicionarTarefa(2L,tarefa).isEmpty());

    }

    @Test
    void findValor(){

        Projeto projeto = new Projeto();
        projeto.setName("Projeto");

        Tarefa tarefa = new Tarefa();
        tarefa.setName("Tarefa");
        tarefa.setTempoEstimado(Duration.ofHours(2));

        Empregado empregado = new Empregado();
        empregado.setName("Nome");
        empregado.setCargo(Empregado.Cargo.AnalistaSenior);

        tarefa.adicionarEmpregado(empregado);
        projeto.adicionarTarefa(tarefa);
        projeto.custoProjeto();
        when(projetoRepository.save(projeto)).thenReturn(projeto);
        assertNotNull(projetoService.findValor(1L));
        //assertEquals(projetoService.findValor(1L), projeto.getValorEstimado());

    }

    @Test
    void findTempo(){

        Projeto projeto = new Projeto();
        projeto.setName("Projeto");

        Tarefa tarefa = new Tarefa();
        tarefa.setName("Tarefa");
        tarefa.setDias(0);
        tarefa.setHoras(2);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        Empregado empregado = new Empregado();
        empregado.setName("Nome");
        empregado.setCargo(Empregado.Cargo.AnalistaSenior);

        tarefa.adicionarEmpregado(empregado);
        projeto.adicionarTarefa(tarefa);
        projeto.custoProjeto();
        when(projetoRepository.save(projeto)).thenReturn(projeto);
        assertNotNull(projetoService.findTempo(1L));
        //assertEquals(projetoService.findTempo(1L), projeto.getTempoEstimado());
    }

}
