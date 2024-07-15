package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
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

        try{
            if(produto.getQuantidadeEstoque() >= dto.getQuantidade()
                    && pedido.getStatus() != StatusPedido.PAGO
                    && pedido.getStatus() != StatusPedido.FINALIZADO
                    && pedido.getStatus() != StatusPedido.ESPERANDO_PAGAMENTO){

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
                pedido.setStatus(StatusPedido.ESPERANDO_PAGAMENTO);
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - dto.getQuantidade());
                pedidoRepository.save(pedido);
                return modelMapper.map(itemPedido, ItemPedidoGetDTO.class);
            } else {
                throw new RuntimeException("Erro ao adicionar item ao pedido. Verifique se há estoque ou se o pedido já foi pago");
            }

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
