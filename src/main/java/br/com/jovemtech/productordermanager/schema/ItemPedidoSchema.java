package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_item_pedido")
public class ItemPedidoSchema {

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemPedidoSchema(ProdutoSchema produto, PedidoSchema pedido, Integer quantidade, BigDecimal preco) {
        id.setProduto(produto);
        id.setPedido(pedido);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoSchema that = (ItemPedidoSchema) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
