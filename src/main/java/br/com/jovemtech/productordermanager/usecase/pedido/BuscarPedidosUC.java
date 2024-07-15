package br.com.jovemtech.productordermanager.usecase.pedido;

import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.PedidoRepository;
import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarPedidosUC {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<PedidoGetDTO> execute(){
        try{
            List<PedidoSchema> pedidos = pedidoRepository.findAll();
            return pedidos.stream().map(pedido -> modelMapper.map(pedido, PedidoGetDTO.class)).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
