package projectesof.esof.services;

import projectesof.esof.models.Empregado;
import projectesof.esof.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaService {

    List<Tarefa> findAll();

    Optional<Tarefa> findById(Long id);

    Optional<Tarefa> createTarefa(Tarefa converter);

    Optional<Tarefa> adicionarEmpregado(Long tarefaId, Empregado empregado);


}
