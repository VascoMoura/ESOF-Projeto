package projectesof.esof.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectesof.esof.models.Empregado;
import projectesof.esof.models.Tarefa;
import projectesof.esof.repositories.TarefaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService{
    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaServiceImpl(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public List<Tarefa> findAll() {
        List<Tarefa> tarefas=new ArrayList<>();
        tarefaRepository.findAll().forEach(tarefas::add);
        return tarefas;
    }

    @Override
    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    @Override
    public Optional<Tarefa> createTarefa(Tarefa tarefa) {
        return Optional.of(tarefaRepository.save(tarefa));
    }


    @Override
    public Optional<Tarefa> adicionarEmpregado(Long tarefaId, Empregado empregado) {

        Optional<Tarefa> optionalTarefa=this.tarefaRepository.findById(tarefaId);

        if(optionalTarefa.isPresent()){
            Tarefa tarefa=optionalTarefa.get();
            tarefa.adicionarEmpregado(empregado);
            tarefaRepository.save(tarefa);
                return Optional.of(tarefa);
        }
        return Optional.empty();
    }
}
