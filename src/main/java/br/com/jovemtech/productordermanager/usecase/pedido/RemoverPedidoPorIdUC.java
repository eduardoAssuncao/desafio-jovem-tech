package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverPedidoPorIdUC {

    private final PedidoRepository pedidoRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void remover(Long id) {
        if(!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Erro ao buscar produto com o id " + id);
        }
        try{
            pedidoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
