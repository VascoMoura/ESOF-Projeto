package projectesof.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projectesof.esof.dtos.EmpregadoCreateDTO;
import projectesof.esof.models.Empregado;
import projectesof.esof.models.Tarefa;
import projectesof.esof.services.TarefaService;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaController.class)
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TarefaService tarefaService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllTarefa() throws Exception {
        Tarefa tarefa1=new Tarefa();
        Tarefa tarefa2=new Tarefa();
        Tarefa tarefa3=new Tarefa();

        List<Tarefa> tarefas= Arrays.asList(tarefa1,tarefa2,tarefa3);

        when(tarefaService.findAll()).thenReturn(tarefas);

        String httpResponseAsString=mockMvc.perform(get("/tarefa")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }

    @Test
    void getTarefaById() throws Exception {
        Tarefa tarefa=new Tarefa();

        when(tarefaService.findById(1L)).thenReturn(Optional.of(tarefa));

        String httpResponse=mockMvc.perform(get("/tarefa/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponse);

        mockMvc.perform(get("/tarefa/2")).andExpect(status().isNotFound());
    }

    @Test
    void createTarefa() throws Exception {

        Tarefa tarefa = new Tarefa();
        tarefa.setName("teste");
        tarefa.setDias(0);
        tarefa.setHoras(2);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));
        when(this.tarefaService.createTarefa(tarefa)).thenReturn(Optional.of(tarefa));
        String tarefaAsJsonString = new ObjectMapper().writeValueAsString(tarefa);
        mockMvc.perform(post("/tarefa").content(tarefaAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Tarefa tarefaExistente = new Tarefa();
        tarefaExistente.setId(1L);
        tarefaExistente.setDias(0);
        tarefaExistente.setHoras(2);
        tarefaExistente.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        String tarefaExistenteAsJsonString = new ObjectMapper().writeValueAsString(tarefaExistente);

        mockMvc.perform(post("/tarefa").content(tarefaExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void adicionarEmpregado() throws Exception {
        Tarefa tarefa = new Tarefa();
        tarefa.setName("Implementacao");
        tarefa.setDias(0);
        tarefa.setHoras(1);
        tarefa.setMinutos(0);
        tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        EmpregadoCreateDTO empregadoCreateDTO = new EmpregadoCreateDTO();
        empregadoCreateDTO.setEmail("empregado@gmail.com");
        empregadoCreateDTO.setName("Nome");
        empregadoCreateDTO.setCargo(Empregado.Cargo.DesenvolvedorJunior);


        String empregadoAsJsonString = objectMapper.writeValueAsString(empregadoCreateDTO.converter());
        when(tarefaService.adicionarEmpregado(1L,empregadoCreateDTO.converter())).thenReturn(Optional.of(tarefa));
        mockMvc.perform(patch("/tarefa/empregado/1").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());




    }
}
