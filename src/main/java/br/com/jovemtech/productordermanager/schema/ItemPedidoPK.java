package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class ItemPedidoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoSchema produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoSchema pedido;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return produto != null && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
