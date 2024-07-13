package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import br.com.jovemtech.productordermanager.schema.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

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
    //private EmpresaDTO empresa;
    private Set<ItemPedidoDTO> itens = new LinkedHashSet<>();

    public PedidoGetDTO(PedidoSchema pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.cliente = new ClienteDTO(pedido.getCliente());
        //this.empresa = new EmpresaDTO(pedido.getEmpresa());
        this.itens.addAll(pedido.getItens().stream().map(ItemPedidoDTO::new).toList());
    }
}
