package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverClientePorIdUC {

    private final ClienteRepository clienteRepository;

    @Transactional
    public void execute(Long id){
        if(!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Erro ao buscar cliente com o id " + id);
        }
        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
