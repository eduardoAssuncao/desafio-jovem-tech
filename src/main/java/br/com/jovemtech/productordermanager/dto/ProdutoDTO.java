package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quatidadeEtoque;
    @JsonIgnore
    private EmpresaDTO empresa;

    public ProdutoDTO(ProdutoSchema produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.quatidadeEtoque = produto.getQuatidadeEtoque();
        this.empresa = new EmpresaDTO(produto.getEmpresa());
    }
}
