package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverPedidosUC {

    private final PedidoRepository pedidoRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void removerPedidos(){
        try{
            pedidoRepository.deleteAll();
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
