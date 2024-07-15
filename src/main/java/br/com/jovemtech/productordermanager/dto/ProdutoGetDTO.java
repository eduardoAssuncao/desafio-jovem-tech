package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProdutoGetDTO extends RepresentationModel<ProdutoGetDTO> {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quatidadeEtoque;
    private EmpresaDTO empresa;

    public ProdutoGetDTO(ProdutoSchema produtoSchema) {
        this.id = produtoSchema.getId();
        this.nome = produtoSchema.getNome();
        this.descricao = produtoSchema.getDescricao();
        this.preco = produtoSchema.getPreco();
        this.quatidadeEtoque = produtoSchema.getQuatidadeEstoque();
        this.empresa = new EmpresaDTO(produtoSchema.getEmpresa());
    }
}
