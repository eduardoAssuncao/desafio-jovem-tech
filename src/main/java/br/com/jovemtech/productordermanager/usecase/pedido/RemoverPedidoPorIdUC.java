package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.StatusPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverPedidoPorIdUC {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(Long id) {
        if(!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Erro ao buscar pedido com o id " + id);
        }
        try{
            PedidoSchema pedido = pedidoRepository.getReferenceById(id);

            if(pedido.getStatus() != StatusPedido.PAGO
                    && pedido.getStatus() != StatusPedido.FINALIZADO){

                pedido.getItens().forEach(itemPedido -> {
                    itemPedido.getId().getProduto().setQuantidadeEstoque(
                            itemPedido.getId().getProduto().getQuantidadeEstoque() + itemPedido.getQuantidade()
                    );
                    produtoRepository.save(itemPedido.getId().getProduto());
                });

                pedidoRepository.deleteById(id);

            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
