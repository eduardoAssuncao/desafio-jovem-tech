package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemPedidoGetDTO {

    private Long produtoId;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemPedidoGetDTO(ItemPedidoSchema itemPedido) {
        this.produtoId = itemPedido.getId().getProduto().getId();
        this.nome = itemPedido.getId().getProduto().getNome();
        this.quantidade = itemPedido.getQuantidade();
        this.preco = itemPedido.getPreco();
    }

    public BigDecimal getSubTotal() {
        return preco.multiply(new BigDecimal(quantidade));
    }
}
