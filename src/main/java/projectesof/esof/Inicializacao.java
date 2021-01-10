package projectesof.esof;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projectesof.esof.models.Empregado;
import projectesof.esof.models.Projeto;
import projectesof.esof.models.Tarefa;
import projectesof.esof.repositories.EmpregadoRepository;
import projectesof.esof.repositories.ProjetoRepository;
import projectesof.esof.repositories.TarefaRepository;

import java.time.Duration;


@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    private final EmpregadoRepository empregadoRepository;
    private final ProjetoRepository projetoRepository;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public Inicializacao(EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository, TarefaRepository tarefaRepository) {
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("\n\n\nInicializou\n\n\n");

        Empregado empregado1 = new Empregado();
        empregado1.setName("Vasco Silva");
        empregado1.setEmail("vasco@gmail.com");
        empregado1.setCargo(Empregado.Cargo.DesenvolvedorJunior);

        Projeto projeto1 = new Projeto();
        projeto1.setName("Projeto Esof");
        projeto1.setValorEstimado(0F);
        Duration tempo = Duration.ofDays(0);
        //Duration tempo = Duration.ofDays(0).plusHours(2).plusMinutes(3);
        projeto1.setTempoEstimado(tempo);

        Tarefa tarefa1 = new Tarefa();
        tarefa1.setName("Planeamento");
        tarefa1.setDias(0);
        tarefa1.setHoras(2);
        tarefa1.setMinutos(0);
        tarefa1.setTempoEstimado(Duration.ofDays(tarefa1.getDias()).plusHours(tarefa1.getHoras()).plusMinutes(tarefa1.getMinutos()));


        projeto1.adicionarTarefa(tarefa1);
        empregado1.adicionarTarefa(tarefa1);


        this.empregadoRepository.save(empregado1);
        this.tarefaRepository.save(tarefa1);
        this.projetoRepository.save(projeto1);



    }
}