package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.PedidoDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarPedidoPorIdUC {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PedidoDTO execute(Long id, PedidoDTO dto) {
        if(!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Erro ao buscar cliente com o id " + id);
        }
        try{
            PedidoSchema pedido = pedidoRepository.getReferenceById(id);
            modelMapper.map(pedido, dto);
            pedidoRepository.save(pedido);
            return modelMapper.map(pedido, PedidoDTO.class);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
