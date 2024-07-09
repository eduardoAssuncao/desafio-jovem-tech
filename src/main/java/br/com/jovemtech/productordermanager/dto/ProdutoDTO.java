package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quatidadeEtoque;

    public ProdutoDTO(ProdutoSchema produtoSchema) {
        this.nome = produtoSchema.getNome();
        this.descricao = produtoSchema.getDescricao();
        this.preco = produtoSchema.getPreco();
        this.quatidadeEtoque = produtoSchema.getQuatidadeEtoque();
    }
}
