package projectesof.esof.dtos.conversores;

import projectesof.esof.dtos.EmpregadoCreateDTO;
import projectesof.esof.dtos.TarefaResponseDTO;
import projectesof.esof.models.Tarefa;

public class ConverterTarefaParaDTO implements Conversor<TarefaResponseDTO, Tarefa> {

    @Override
    public TarefaResponseDTO converter(Tarefa tarefa) {
        TarefaResponseDTO responseDTO = new TarefaResponseDTO();
        responseDTO.setName(tarefa.getName());
        responseDTO.setTempoEstimado(tarefa.getTempoEstimado());
        responseDTO.setValorEstimado(tarefa.getValorEstimado());
        if(tarefa.getEmpregado() != null)
        {
            EmpregadoCreateDTO empregadoCreateDTO = new EmpregadoCreateDTO();
            empregadoCreateDTO.setName(tarefa.getEmpregado().getName());
            empregadoCreateDTO.setEmail(tarefa.getEmpregado().getEmail());
            empregadoCreateDTO.setCargo(tarefa.getEmpregado().getCargo());
            responseDTO.setEmpregado(empregadoCreateDTO);
        }
        return responseDTO;
    }
}