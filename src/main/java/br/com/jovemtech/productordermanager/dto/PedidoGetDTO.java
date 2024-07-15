package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoGetDTO {

    private Long id;
    private Instant dataPedido;
    private StatusPedido status;
    private ClienteDTO cliente;
    private Set<ItemPedidoDTO> itens = new LinkedHashSet<>();

    public PedidoGetDTO(PedidoSchema pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.cliente = new ClienteDTO(pedido.getCliente());
        this.itens.addAll(pedido.getItens().stream().map(ItemPedidoDTO::new).toList());
    }
}
