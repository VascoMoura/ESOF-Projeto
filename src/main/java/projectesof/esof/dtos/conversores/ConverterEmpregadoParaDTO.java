package projectesof.esof.dtos.conversores;

import projectesof.esof.dtos.EmpregadoResponseDTO;
import projectesof.esof.models.Empregado;


public class ConverterEmpregadoParaDTO implements Conversor<EmpregadoResponseDTO, Empregado>{

    @Override
        public EmpregadoResponseDTO converter(Empregado empregado) {
            EmpregadoResponseDTO responseDTO = new EmpregadoResponseDTO();
            responseDTO.setName(empregado.getName());
            responseDTO.setEmail(empregado.getEmail());
            responseDTO.setCargo(empregado.getCargo());
            return responseDTO;
    }
}
