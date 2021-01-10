package projectesof.esof.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projectesof.esof.models.Empregado;
import projectesof.esof.repositories.EmpregadoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpregadoController.class)
public class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpregadoController empregadoController;
    private EmpregadoRepository empregadoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmpregado() throws Exception {
        Empregado empregado1=new Empregado();
        Empregado empregado2=new Empregado();
        Empregado empregado3=new Empregado();

        List<Empregado> empregados= Arrays.asList(empregado1,empregado2,empregado3);

        String listEmpregadosAsJsonString=new ObjectMapper().writeValueAsString(empregados);

        when(empregadoRepository.findAll()).thenReturn(empregados);

        String httpResponseAsString=mockMvc.perform(get("/empregado")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        assertEquals(listEmpregadosAsJsonString,httpResponseAsString);
    }

    @Test
    void getEmpregadoById() throws Exception {
        Empregado empregado=new Empregado();
        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregado);

        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));

        String httpResponseAsString=mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
        assertEquals(empregadoAsJsonString,httpResponseAsString);

        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound());
    }

    @Test
    void createEmpregado() throws Exception {
        Empregado empregado=new Empregado();
        empregado.setEmail("novoempregado@mail.com");

        when(this.empregadoRepository.save(empregado)).thenReturn(empregado);

        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregado);

        mockMvc.perform(post("/empregado").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Empregado empregadoExistente=new Empregado();
        empregadoExistente.setEmail("empregado@mail.com");
        String empregadoExistenteAsJsonString=new ObjectMapper().writeValueAsString(empregadoExistente);
        when(this.empregadoRepository.findByEmail("empregado@mail.com")).thenReturn(Optional.of(empregadoExistente));

        mockMvc.perform(post("/empregado").content(empregadoExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }

}
