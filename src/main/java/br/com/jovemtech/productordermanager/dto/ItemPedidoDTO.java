package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {

    @JsonIgnore
    private Long produtoId;
    @JsonIgnore
    private String nome;
    @NotNull(message = "A quantidade não pode estar em branco")
    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantidade;
    @JsonIgnore
    private BigDecimal preco;

    public ItemPedidoDTO(ItemPedidoSchema itemPedido) {
        this.produtoId = itemPedido.getId().getProduto().getId();
        this.nome = itemPedido.getNome();
        this.quantidade = itemPedido.getQuantidade();
        this.preco = itemPedido.getPreco();
    }
}
