package projectesof.esof.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectesof.esof.models.Empregado;
import projectesof.esof.repositories.EmpregadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class EmpregadoServiceImpl implements EmpregadoService {
    private final EmpregadoRepository empregadoRepository;

    @Autowired
    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    @Override
    public List<Empregado> findAll() {
        List<Empregado> empregados=new ArrayList<>();
        empregadoRepository.findAll().forEach(empregados::add);
        return empregados;
    }

    @Override
    public Optional<Empregado> findById(Long id) {
        return empregadoRepository.findById(id);
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado) {
        Optional<Empregado> optionalEmpregado=empregadoRepository.findByEmail(empregado.getEmail());
        if(optionalEmpregado.isEmpty()){
            return Optional.of(empregadoRepository.save(empregado));
        }
        return Optional.empty();

    }
}