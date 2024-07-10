package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class PedidoDTO {

    private Instant dataPedido;
    private StatusPedido status;
    private ClienteDTO cliente;
    private EmpresaDTO empresa;
    private Set<ItemPedidoDTO> itens = new LinkedHashSet<>();

    public PedidoDTO(Instant dataPedido, StatusPedido status, ClienteDTO cliente, EmpresaDTO empresa) {
        this.dataPedido = dataPedido;
        this.status = status;
        this.cliente = cliente;
        this.empresa = empresa;
    }

    public PedidoDTO(PedidoSchema pedido) {
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.cliente = new ClienteDTO(pedido.getCliente());
        this.empresa = new EmpresaDTO(pedido.getEmpresa());
        this.itens.addAll(pedido.getItens().stream().map(ItemPedidoDTO::new).toList());
    }
}
