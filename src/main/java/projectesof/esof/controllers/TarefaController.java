package projectesof.esof.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;

import projectesof.esof.dtos.EmpregadoCreateDTO;
import projectesof.esof.dtos.TarefaCreateDTO;
import projectesof.esof.dtos.TarefaResponseDTO;
import projectesof.esof.dtos.conversores.ConverterTarefaParaDTO;

import projectesof.esof.models.Tarefa;
import projectesof.esof.services.TarefaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    private final ConverterTarefaParaDTO converterTarefaParaDTO=new ConverterTarefaParaDTO();



    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<TarefaResponseDTO>> getAllTarefa(){
        List<TarefaResponseDTO> responseDTOS=new ArrayList<>();
        tarefaService.findAll().forEach(tarefa -> responseDTOS.add(converterTarefaParaDTO.converter(tarefa)));
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> getTarefaById(@PathVariable Long id){
        Optional<Tarefa> optionalTarefa=tarefaService.findById(id);
        return optionalTarefa.map(tarefa -> {
            TarefaResponseDTO tarefaResponseDTO=converterTarefaParaDTO.converter(tarefa);
            return ResponseEntity.ok(tarefaResponseDTO);
        }).orElseGet(()->ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<TarefaResponseDTO> createTarefa(@RequestBody TarefaCreateDTO tarefa){
        Optional<Tarefa> optionalTarefa=tarefaService.createTarefa(tarefa.converter());
        return optionalTarefa.map(value -> ResponseEntity.ok(converterTarefaParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PatchMapping("/empregado/{idTarefa}")
    public ResponseEntity<TarefaResponseDTO> adicionarEmpregado(@PathVariable Long idTarefa, @RequestBody EmpregadoCreateDTO empregado){
        Optional<Tarefa> optionalTarefa=tarefaService.adicionarEmpregado(idTarefa,empregado.converter());
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
