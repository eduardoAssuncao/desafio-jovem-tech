package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String descricao;

    @NotNull(message = "O preço não pode estar em branco")
    @DecimalMin(value = "0.00", inclusive = false, message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A quantidade em estoque não pode estar em branco")
    private Integer quantidadeEstoque;

    public ProdutoDTO(ProdutoSchema produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
    }
}
