package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.dto.ClienteGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarClientesUC {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Page<ClienteGetDTO> execute(Pageable pageable){
        try{
            //Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.unsorted());
            Page<ClienteSchema> clientes = clienteRepository.findAll(pageable);
            return clientes.map(cliente ->
                    modelMapper.map(cliente, ClienteGetDTO.class));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
