package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Instant dataPedido;
    private StatusPedido status;
    private ClienteDTO cliente;
    private EmpresaDTO empresa;
    private Set<ItemPedidoDTO> itens = new LinkedHashSet<>();

    public PedidoDTO(PedidoSchema pedido) {
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.cliente = new ClienteDTO(pedido.getCliente());
        this.empresa = new EmpresaDTO(pedido.getEmpresa());
        this.itens.addAll(pedido.getItens().stream().map(ItemPedidoDTO::new).toList());
    }
}
