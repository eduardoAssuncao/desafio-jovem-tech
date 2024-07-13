package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ItemPedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CriarPedidoUC {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public PedidoGetDTO execute(PedidoDTO dto, Long idCliente){

        PedidoSchema pedido = new PedidoSchema();
        pedido.setDataPedido(Instant.now());
        pedido.setStatus(StatusPedido.PROCESSAMENTO);

        for(ItemPedidoDTO itemDto : dto.getItens()){
            ProdutoSchema produto = produtoRepository.getReferenceById(itemDto.getProdutoId());
            ItemPedidoSchema item = new ItemPedidoSchema(produto, pedido, itemDto.getQuantidade(), produto.getPreco());
            pedido.getItens().add(item);
        }

        ClienteSchema cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        cliente.getPedidos().add(pedido);

        pedidoRepository.save(pedido);
        clienteRepository.save(cliente);
        itemPedidoRepository.saveAll(pedido.getItens());

        return modelMapper.map(pedido, PedidoGetDTO.class);
    }
}
