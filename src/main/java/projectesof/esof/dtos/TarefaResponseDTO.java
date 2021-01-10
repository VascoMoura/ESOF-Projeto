package projectesof.esof.dtos;

import lombok.Data;

import java.time.Duration;

@Data
public class TarefaResponseDTO {
    private String name;
    private Duration tempoEstimado;
    private Float valorEstimado;
    private EmpregadoCreateDTO empregado;


}
