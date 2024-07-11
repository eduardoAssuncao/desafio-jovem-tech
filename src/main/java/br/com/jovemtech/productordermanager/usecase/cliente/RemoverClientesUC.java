package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverClientesUC {

    private final ClienteRepository clienteRepository;

    @Transactional
    public void execute(){
        try{
            clienteRepository.deleteAll();
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
