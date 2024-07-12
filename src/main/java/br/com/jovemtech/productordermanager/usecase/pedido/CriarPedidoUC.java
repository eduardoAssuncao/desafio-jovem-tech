package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ItemPedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import br.com.jovemtech.productordermanager.schema.StatusPedido;
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
    private final ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public PedidoGetDTO execute(PedidoGetDTO dto){

        PedidoSchema pedido = new PedidoSchema();

        pedido.setDataPedido(Instant.now());
        pedido.setStatus(StatusPedido.ESPERANDO_PAGAMENTO);

        for(ItemPedidoDTO itemDto : dto.getItens()){
            ProdutoSchema produto = produtoRepository.getReferenceById(itemDto.getProdutoId());
            ItemPedidoSchema item = new ItemPedidoSchema(produto, pedido, itemDto.getQuantidade(), produto.getPreco());
            pedido.getItens().add(item);
        }

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());

        return modelMapper.map(pedido, PedidoGetDTO.class);
    }
}
