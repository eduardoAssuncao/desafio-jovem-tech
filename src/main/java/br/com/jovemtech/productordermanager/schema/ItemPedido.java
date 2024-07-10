package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Integer quantidade;
    private BigDecimal preco;

    public ItemPedido(ProdutoSchema produto, PedidoSchema pedido,Integer quantidade, BigDecimal preco) {
        id.setProduto(produto);
        id.setPedido(pedido);
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
