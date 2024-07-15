package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ItemPedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverItemDoPedidoUC {

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ItemPedidoGetDTO execute(Long idPedido, Long idProduto) {
        PedidoSchema pedido = pedidoRepository.findById(idPedido).orElseThrow(
                () -> new EntityNotFoundException("Pedido não encontrado"));
        ProdutoSchema produto = produtoRepository.findById(idProduto).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado"));

        ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
        itemPedidoPK.setPedido(pedido);
        itemPedidoPK.setProduto(produto);

        ItemPedidoSchema itemPedido = itemPedidoRepository.findById(itemPedidoPK).orElseThrow(
                () -> new EntityNotFoundException("Item do pedido não encontrado"));

        if (pedido.getStatus() != StatusPedido.PAGO
                && pedido.getStatus() != StatusPedido.FINALIZADO
                && pedido.getStatus() != StatusPedido.PROCESSAMENTO) {

            pedido.getItens().remove(itemPedido);
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + itemPedido.getQuantidade());

            itemPedidoRepository.delete(itemPedido);
            pedidoRepository.save(pedido);
            produtoRepository.save(produto);

            return modelMapper.map(itemPedido, ItemPedidoGetDTO.class);
        } else {
            throw new RuntimeException("Erro ao remover item do pedido. Verifique se o pedido já foi pago ou finalizado.");
        }
    }

}
