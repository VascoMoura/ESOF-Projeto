package projectesof.esof.dtos;

import lombok.Data;
import projectesof.esof.models.Projeto;

import java.time.Duration;

@Data
public class ProjetoCreateDTO implements CreateDTO<Projeto>{
    private String name;

    @Override
    public Projeto converter() {
        Projeto projeto=new Projeto();
        projeto.setName(this.getName());
        Duration tempo = Duration.ofDays(0);
        projeto.setTempoEstimado(tempo);
        projeto.setValorEstimado(0F);
        return projeto;
    }
}
