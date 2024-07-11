package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarClienteUC {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ClienteDTO execute(ClienteDTO dto) {
        ClienteSchema cliente = clienteRepository
                .save(modelMapper.map(dto, ClienteSchema.class));
        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
