package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoDTO {

    @JsonIgnore
    private Instant dataPedido;
    @JsonIgnore
    private StatusPedido status;
    @JsonIgnore
    private ClienteDTO cliente;
    @JsonIgnore
    private Set<ItemPedidoDTO> itens = new LinkedHashSet<>();

    public PedidoDTO(PedidoSchema pedido) {
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.cliente = new ClienteDTO(pedido.getCliente());
        this.itens.addAll(pedido.getItens().stream().map(ItemPedidoDTO::new).toList());
    }
}
