package projectesof.esof.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectesof.esof.models.Empregado;

import java.util.Optional;

@Repository
public interface EmpregadoRepository extends CrudRepository<Empregado,Long> {
    Optional<Empregado> findByEmail(String email);
}
