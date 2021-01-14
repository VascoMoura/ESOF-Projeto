package projectesof.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projectesof.esof.models.Empregado;
import projectesof.esof.services.EmpregadoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpregadoController.class)
public class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpregadoService empregadoService;

    @Test
    void getAllEmpregado() throws Exception {
        Empregado empregado1=new Empregado();
        Empregado empregado2=new Empregado();
        Empregado empregado3=new Empregado();

        List<Empregado> empregados= Arrays.asList(empregado1,empregado2,empregado3);



        when(empregadoService.findAll()).thenReturn(empregados);

        String httpResponseAsString=mockMvc.perform(get("/empregado")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);


    }

    @Test
    void getEmpregadoById() throws Exception {
        Empregado empregado=new Empregado();

        when(empregadoService.findById(1L)).thenReturn(Optional.of(empregado));

        String httpResponse=mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponse);

        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound());
    }

    @Test
    void createEmpregado() throws Exception {
        Empregado empregado=new Empregado();
        empregado.setEmail("novoempregado@mail.com");

        when(this.empregadoService.createEmpregado(empregado)).thenReturn(Optional.of(empregado));

        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregado);

        mockMvc.perform(post("/empregado").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Empregado empregadoExistente=new Empregado();
        empregado.setEmail("empregado@mail.com");
        String empregadoExistenteAsString=new ObjectMapper().writeValueAsString(empregadoExistente);

        mockMvc.perform(post("/empregado").content(empregadoExistenteAsString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }
}
