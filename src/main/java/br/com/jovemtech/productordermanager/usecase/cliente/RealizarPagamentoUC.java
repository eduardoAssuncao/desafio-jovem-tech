package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.StatusPedido;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RealizarPagamentoUC {

    private final ClienteRepository clienteRepository;

    private final PedidoRepository pedidoRepository;

    @Transactional
    public PedidoGetDTO execute(Long idCliente, Long idPedido){
        if(!clienteRepository.existsById(idCliente)){
            throw new ResourceNotFoundException("Erro ao buscar cliente com o id " + idCliente);
        }
        if(!pedidoRepository.existsById(idPedido)){
            throw new ResourceNotFoundException("Erro ao buscar cliente com o id " + idPedido);
        }
        try{

            ClienteSchema cliente = clienteRepository.getReferenceById(idCliente);
            PedidoSchema pedido = pedidoRepository.getReferenceById(idPedido);

            if(!cliente.getPedidos().contains(pedido)){
                throw new ResourceNotFoundException("Pedido com id " + idPedido + " não pertence ao cliente com id " + idCliente);
            }

            pedido.setStatus(StatusPedido.PAGO);
            pedidoRepository.save(pedido);
            return new PedidoGetDTO(pedido);

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Erro ao buscar cliente ou pedido com os ids fornecidos");
        }
    }
}
