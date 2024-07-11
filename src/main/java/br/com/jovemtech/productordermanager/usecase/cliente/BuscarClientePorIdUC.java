package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarClientePorIdUC {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public ClienteDTO execute(Long id){
        ClienteSchema cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Erro ao buscar cliente com o id: " + id));
        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
