package projectesof.esof.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectesof.esof.models.Projeto;

import java.util.Optional;
@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long>{
    Optional<Projeto> findByName(String name);

}
