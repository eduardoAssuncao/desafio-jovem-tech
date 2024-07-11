package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarClientePorIdUC {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ClienteDTO execute(Long id, ClienteDTO dto) {
        if(!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Erro ao buscar cliente com o id " + id);
        }
        try{
            ClienteSchema cliente = clienteRepository.getReferenceById(id);
            modelMapper.map(dto, cliente);
            clienteRepository.save(cliente);
            return modelMapper.map(cliente, ClienteDTO.class);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
