package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoDTO {

    private Integer quantidade;
    private BigDecimal preco;

    public ItemPedidoDTO(ItemPedidoSchema itemPedido) {
        this.quantidade = itemPedido.getQuantidade();
        this.preco = itemPedido.getPreco();
    }
}
