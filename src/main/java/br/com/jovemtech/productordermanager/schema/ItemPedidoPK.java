package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ItemPedidoPK {

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoSchema produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoSchema pedido;

}
