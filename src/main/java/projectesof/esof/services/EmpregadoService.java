package projectesof.esof.services;

import projectesof.esof.models.Empregado;

import java.util.List;
import java.util.Optional;

public interface EmpregadoService {
    List<Empregado> findAll();


    Optional<Empregado> findById(Long id);

    Optional<Empregado> createEmpregado(Empregado converter);

}