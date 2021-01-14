package projectesof.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projectesof.esof.models.Projeto;
import projectesof.esof.services.ProjetoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProjeto() throws Exception {
        Projeto projeto1=new Projeto();
        Projeto projeto2=new Projeto();

        List<Projeto> projetos= Arrays.asList(projeto1,projeto2);

        //String listProjetosAsJsonString=new ObjectMapper().writeValueAsString(projetos);

        when(projetoService.findAll()).thenReturn(projetos);

        String httpResponseAsString=mockMvc.perform(get("/projeto")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getProjetoById() throws Exception {
        Projeto projeto=new Projeto();

        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));

        String httpResponse=mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponse);

        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }


    @Test
    void createProjeto() throws Exception{
        Projeto projeto = new Projeto();
        projeto.setName("Projeto");

        when(this.projetoService.createProjeto(projeto)).thenReturn(Optional.of(projeto));
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);
        mockMvc.perform(post("/projeto").content(projetoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Projeto projeto1 = new Projeto();
        projeto1.setName("Projeto Esof");
        String projeto1AsString = new ObjectMapper().writeValueAsString(projeto1);
        mockMvc.perform(post("/projeto").content(projeto1AsString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }
/*
    @Test
    void adicionarTarefa() throws Exception{
        Projeto projeto = new Projeto();
        projeto.setName("Projeto novo");

        TarefaCreateDTO tarefa = new TarefaCreateDTO();
        tarefa.setName("tarefaTest");
        tarefa.setDias(0);
        tarefa.setHoras(1);
        tarefa.setMinutos(0);
        //tarefa.setTempoEstimado(Duration.ofDays(tarefa.getDias()).plusHours(tarefa.getHoras()).plusMinutes(tarefa.getMinutos()));

        String tarefaJson = objectMapper.writeValueAsString(tarefa);
        when(projetoService.adicionarTarefa(1L, tarefa.converter())).thenReturn(Optional.of(projeto));

        mockMvc.perform(patch("/projeto/tarefa/1").contentType(tarefaJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        mockMvc.perform(patch("/projeto/tarefa/2").contentType(tarefaJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void getProjetoValor() throws Exception{

    }

    @Test
    void getProjetoTempo() throws Exception{

    }
*/
}