package projectesof.esof.dtos.conversores;

import projectesof.esof.dtos.ProjetoResponseDTO;
import projectesof.esof.dtos.TarefaCreateDTO;
import projectesof.esof.models.Projeto;

import java.util.stream.Collectors;

public class ConverterProjetoParaDTO implements Conversor<ProjetoResponseDTO,Projeto>{

   @Override
    public ProjetoResponseDTO converter(Projeto projeto) {
        ProjetoResponseDTO responseDTO = new ProjetoResponseDTO();
        responseDTO.setName(projeto.getName());
        projeto.custoProjeto();
        responseDTO.setValorEstimado(projeto.getValorEstimado());
        responseDTO.setTempoEstimado(projeto.getTempoEstimado());
        responseDTO.setTarefas(projeto.getTarefas().stream().map(tarefa -> {
            TarefaCreateDTO tarefaDTO=new TarefaCreateDTO();
            tarefaDTO.setName(tarefa.getName());
            tarefaDTO.setDias(tarefa.getDias());
            tarefaDTO.setHoras(tarefa.getHoras());
            tarefaDTO.setMinutos(tarefa.getMinutos());
            return tarefaDTO;
        }).collect(Collectors.toList()));

       return responseDTO;
   }
}

