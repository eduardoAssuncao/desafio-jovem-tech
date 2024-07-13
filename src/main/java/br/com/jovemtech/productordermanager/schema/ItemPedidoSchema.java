package br.com.jovemtech.productordermanager.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_item_pedido")
public class ItemPedidoSchema {

    @EmbeddedId
    @JsonIgnore
    private ItemPedidoPK id = new ItemPedidoPK();

    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemPedidoSchema(ProdutoSchema produto, PedidoSchema pedido, Integer quantidade, BigDecimal preco) {
        id.setProduto(produto);
        id.setPedido(pedido);
        this.nome = produto.getNome();
        this.quantidade = quantidade;
        this.preco = produto.getPreco();
    }

    public BigDecimal getSubTotal() {
        return preco.multiply(new BigDecimal(quantidade));
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
