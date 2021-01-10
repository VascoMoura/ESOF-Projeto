package projectesof.esof.dtos;

import lombok.Data;
import projectesof.esof.models.Empregado;

@Data
public class EmpregadoResponseDTO {
    private String name;
    private String email;
    private Empregado.Cargo cargo;
}
