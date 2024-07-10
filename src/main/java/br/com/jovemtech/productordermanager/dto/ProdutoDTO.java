package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quatidadeEtoque;
    private EmpresaDTO empresa;

    public ProdutoDTO(ProdutoSchema produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.quatidadeEtoque = produto.getQuatidadeEtoque();
        this.empresa = new EmpresaDTO(produto.getEmpresa());
    }
}
