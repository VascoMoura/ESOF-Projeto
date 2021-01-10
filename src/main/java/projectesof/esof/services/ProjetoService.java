package projectesof.esof.services;

import projectesof.esof.models.Projeto;
import projectesof.esof.models.Tarefa;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface ProjetoService {

    List<Projeto> findAll();

    Optional<Projeto> findById(Long id);

    Optional<Projeto> createProjeto(Projeto converter);

    Optional<Projeto> adicionarTarefa(Long projetoId, Tarefa tarefa);

    Float findValor(Long id);

    Duration findTempo(Long id);
}
