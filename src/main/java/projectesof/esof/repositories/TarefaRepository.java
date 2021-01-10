package projectesof.esof.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectesof.esof.models.Tarefa;


@Repository
public interface TarefaRepository extends CrudRepository<Tarefa,Long>{

}
