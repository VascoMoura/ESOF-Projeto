package projectesof.esof.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import projectesof.esof.dtos.EmpregadoCreateDTO;
import projectesof.esof.dtos.EmpregadoResponseDTO;
import projectesof.esof.dtos.conversores.ConverterEmpregadoParaDTO;
import projectesof.esof.models.Empregado;
import projectesof.esof.services.EmpregadoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    private final EmpregadoService empregadoService;
    private final ConverterEmpregadoParaDTO converterEmpregadoParaDTO=new ConverterEmpregadoParaDTO();



    @Autowired
    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<EmpregadoResponseDTO>> getAllEmpregado(){
        List<EmpregadoResponseDTO> responseDTOS=new ArrayList<>();
        empregadoService.findAll().forEach(empregado -> responseDTOS.add(converterEmpregadoParaDTO.converter(empregado)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpregadoResponseDTO> getEmpregadoById(@PathVariable Long id){
        Optional<Empregado> optionalEmpregado=empregadoService.findById(id);
        return optionalEmpregado.map(empregado -> {
            EmpregadoResponseDTO empregadoResponseDTO=converterEmpregadoParaDTO.converter(empregado);
            return ResponseEntity.ok(empregadoResponseDTO);
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpregadoResponseDTO> createEmpregado(@RequestBody EmpregadoCreateDTO empregado){
        Optional<Empregado> optionalEmpregado=empregadoService.createEmpregado(empregado.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(converterEmpregadoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
