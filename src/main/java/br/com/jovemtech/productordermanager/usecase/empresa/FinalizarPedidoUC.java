package br.com.jovemtech.productordermanager.usecase.empresa;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.StatusPedido;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FinalizarPedidoUC {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PedidoGetDTO execute(long idPedido){
        if(!pedidoRepository.existsById(idPedido)){
            throw new ResourceNotFoundException("Erro ao buscar pedido com o id " + idPedido);
        }
        try{
            PedidoSchema pedido = pedidoRepository.getReferenceById(idPedido);
            if(pedido.getStatus().equals(StatusPedido.PAGO)){
                pedido.setStatus(StatusPedido.FINALIZADO);
            } else {
                throw new RuntimeException("Ainda não foi realizado o pagamento do pedido com id " + idPedido);
            }
            pedidoRepository.save(pedido);
            return modelMapper.map(pedido, PedidoGetDTO.class);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
