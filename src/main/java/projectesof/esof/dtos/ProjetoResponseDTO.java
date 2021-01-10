package projectesof.esof.dtos;

import lombok.Data;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private String name;
    private Float valorEstimado;
    private Duration tempoEstimado;
    private List<TarefaCreateDTO> tarefas=new ArrayList<>();
}
