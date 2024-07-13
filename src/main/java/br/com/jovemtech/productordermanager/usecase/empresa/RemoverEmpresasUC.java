package br.com.jovemtech.productordermanager.usecase.empresa;

import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverEmpresasUC {

    private final EmpresaRepository empresaRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void execute() {
        try{
            empresaRepository.deleteAll();
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
