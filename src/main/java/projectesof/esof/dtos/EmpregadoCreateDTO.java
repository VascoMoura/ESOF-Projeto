package projectesof.esof.dtos;

import lombok.Data;
import projectesof.esof.models.Empregado;

@Data
public class EmpregadoCreateDTO implements CreateDTO<Empregado>{
    private String name;
    private String email;
    private Empregado.Cargo cargo;


    @Override
    public Empregado converter(){
        Empregado empregado=new Empregado();
        empregado.setName(this.getName());
        empregado.setEmail(this.getEmail());
        empregado.setCargo(this.getCargo());

        return empregado;
    }

}
