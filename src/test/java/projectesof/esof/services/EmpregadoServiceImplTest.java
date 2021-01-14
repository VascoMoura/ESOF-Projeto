package projectesof.esof.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import projectesof.esof.models.Empregado;
import projectesof.esof.repositories.EmpregadoRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmpregadoServiceImpl.class)
public class EmpregadoServiceImplTest {

    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void createEmpregado(){
        Empregado empregado = new Empregado();
        empregado.setName("Ruben");
        empregado.setEmail("rubinho@hotmail.com");
        empregado.setCargo(Empregado.Cargo.AnalistaJunior);

        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        assertTrue(empregadoService.createEmpregado(empregado).isPresent());

        when(empregadoRepository.findByEmail(empregado.getEmail())).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.createEmpregado(empregado).isEmpty());
    }

    @Test
    void findAll() {
        when(empregadoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAll());
    }

    @Test
    void findById() {
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findById(1L).isPresent());
        assertTrue(empregadoService.findById(2L).isEmpty());
    }
}
