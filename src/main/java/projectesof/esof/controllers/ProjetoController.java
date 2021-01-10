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

import projectesof.esof.dtos.TarefaCreateDTO;
import projectesof.esof.dtos.conversores.ConverterProjetoParaDTO;
import projectesof.esof.models.Projeto;
import projectesof.esof.services.ProjetoService;
import projectesof.esof.dtos.ProjetoCreateDTO;
import projectesof.esof.dtos.ProjetoResponseDTO;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO=new ConverterProjetoParaDTO();


    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }


    @GetMapping()
    public ResponseEntity<Iterable<ProjetoResponseDTO>> getAllProjeto(){
        List<ProjetoResponseDTO> responseDTOS=new ArrayList<>();
        projetoService.findAll().forEach(projeto -> responseDTOS.add(converterProjetoParaDTO.converter(projeto)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> getProjetoById(@PathVariable Long id){
        Optional<Projeto> optionalProjeto=projetoService.findById(id);
        return optionalProjeto.map(projeto -> {
            ProjetoResponseDTO projetoResponseDTO=converterProjetoParaDTO.converter(projeto);
            return ResponseEntity.ok(projetoResponseDTO);
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> createProjeto(@RequestBody ProjetoCreateDTO projeto){
        Optional<Projeto> optionalProjeto=projetoService.createProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(converterProjetoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/tarefa/{idProjeto}")
    public ResponseEntity<ProjetoResponseDTO> adicionarTarefa(@PathVariable Long idProjeto, @RequestBody TarefaCreateDTO tarefa){
        Optional<Projeto> optionalProjeto=projetoService.adicionarTarefa(idProjeto,tarefa.converter());
        return optionalProjeto.map(projeto -> ResponseEntity.ok(converterProjetoParaDTO.converter(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{idProjeto}/valor")
    public ResponseEntity<Float> getProjetoValor(@PathVariable Long idProjeto){

        Float valor = projetoService.findValor(idProjeto);
        if ( valor >= 0){
            return ResponseEntity.ok(valor);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{idProjeto}/tempo")
    public ResponseEntity<Duration> getProjetoTempo(@PathVariable Long idProjeto){

        Duration tempo = projetoService.findTempo(idProjeto);
        if ( tempo != null){
            return ResponseEntity.ok(tempo);
        }
        return ResponseEntity.notFound().build();
    }
}
