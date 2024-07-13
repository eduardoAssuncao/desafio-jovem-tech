package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.ItemPedidoGetDTO;
import br.com.jovemtech.productordermanager.dto.PedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ItemPedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ItemPedidoPK;
import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdicionarItemAoPedidoUC {

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ItemPedidoGetDTO execute(Long idPedido, Long idProduto, ItemPedidoDTO dto) {
        PedidoSchema pedido = pedidoRepository.findById(idPedido).orElseThrow(
                () -> new EntityNotFoundException("Pedido não encontrado"));
        ProdutoSchema produto = produtoRepository.findById(idProduto).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado"));

        ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
        itemPedidoPK.setPedido(pedido);
        itemPedidoPK.setProduto(produto);

        ItemPedidoSchema itemPedido = new ItemPedidoSchema();
        itemPedido.setNome(produto.getNome());
        itemPedido.setId(itemPedidoPK);
        itemPedido.setQuantidade(dto.getQuantidade());
        itemPedido.setPreco(produto.getPreco());

        itemPedidoRepository.save(itemPedido);

        pedido.getItens().add(itemPedido);
        produto.setQuatidadeEtoque(produto.getQuatidadeEtoque() - dto.getQuantidade());
        pedidoRepository.save(pedido);
        return modelMapper.map(itemPedido, ItemPedidoGetDTO.class);
    }
}
