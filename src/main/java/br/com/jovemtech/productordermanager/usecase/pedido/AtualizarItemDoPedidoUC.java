package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ItemPedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarItemDoPedidoUC {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PedidoGetDTO execute(Long idPedido, Long idProduto, ItemPedidoDTO dto){
        PedidoSchema pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        ProdutoSchema produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        ItemPedidoSchema itemPedido = pedido.getItens().stream()
                .filter(item -> item.getId().getProduto().equals(produto))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item do pedido não encontrado"));

        int novaQuantidadeEstoque = produto.getQuantidadeEstoque() + itemPedido.getQuantidade() - dto.getQuantidade();
        if (novaQuantidadeEstoque < 0) {
            throw new RuntimeException("Quantidade insuficiente em estoque para realizar a atualização");
        }
        produto.setQuantidadeEstoque(novaQuantidadeEstoque);

        itemPedido.setQuantidade(dto.getQuantidade());

        produtoRepository.save(produto);
        pedidoRepository.save(pedido);
        itemPedidoRepository.save(itemPedido);

        return modelMapper.map(pedido, PedidoGetDTO.class);
    }
}
