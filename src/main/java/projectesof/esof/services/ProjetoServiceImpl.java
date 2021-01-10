package projectesof.esof.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectesof.esof.repositories.ProjetoRepository;
import projectesof.esof.models.Tarefa;
import projectesof.esof.models.Projeto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService{

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }


    @Override
    public List<Projeto> findAll() {
        List<Projeto> projetos=new ArrayList<>();
        projetoRepository.findAll().forEach(projetos::add);
        return projetos;
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    @Override
    public Optional<Projeto> createProjeto(Projeto projeto) {
        Optional<Projeto> optionalProjeto=projetoRepository.findByName(projeto.getName());
        if(optionalProjeto.isEmpty()){
            return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();

    }

    @Override
    public Optional<Projeto> adicionarTarefa(Long projetoId, Tarefa tarefa) {

        Optional<Projeto> optionalProjeto=this.projetoRepository.findById(projetoId);


        if(optionalProjeto.isPresent()){
            Projeto projeto=optionalProjeto.get();

            int quantidadeDeTarefasAntes=projeto.getTarefas().size();
            projeto.adicionarTarefa(tarefa);
            projetoRepository.save(projeto);
            int quantidadeDeTarefasDepois=projeto.getTarefas().size();
            if(quantidadeDeTarefasAntes!=quantidadeDeTarefasDepois) {
                return Optional.of(projeto);
            }
        }
        return Optional.empty();
    }


    @Override
    public Float findValor(Long id) {
        Optional<Projeto> optionalProjeto=this.projetoRepository.findById(id);
        if(optionalProjeto.isPresent()){
            Projeto projeto=optionalProjeto.get();
            projeto.custoProjeto();
            return projeto.getValorEstimado();
        }
        return -1F;
    }

    @Override
    public Duration findTempo(Long id) {
        Optional<Projeto> optionalProjeto=this.projetoRepository.findById(id);
        if(optionalProjeto.isPresent()){
            Projeto projeto=optionalProjeto.get();
            projeto.custoProjeto();
            return projeto.getTempoEstimado();
        }
        return null;
    }
}
