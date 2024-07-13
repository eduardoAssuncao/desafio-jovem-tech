package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarPedidoPorIdUC {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PedidoGetDTO execute(Long id) {
        PedidoSchema pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Erro ao buscar produto com o id " + id));
        return modelMapper.map(pedido, PedidoGetDTO.class);
    }
}
