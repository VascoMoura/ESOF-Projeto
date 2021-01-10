package projectesof.esof.dtos;

import lombok.Data;
import projectesof.esof.models.Tarefa;

import java.time.Duration;

@Data
public class TarefaCreateDTO implements CreateDTO<Tarefa>{
    private String name;
    private Integer dias;
    private Integer horas;
    private Integer minutos;

    @Override
    public Tarefa converter() {
        Tarefa tarefa = new Tarefa();
        tarefa.setName(this.getName());
        tarefa.setDias(this.getDias());
        tarefa.setHoras(this.getHoras());
        tarefa.setMinutos(this.getMinutos());
        Duration tempo = Duration.ofDays(dias).plusHours(horas).plusMinutes(minutos);
        tarefa.setTempoEstimado(tempo);
        return tarefa;
    }
}
