package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_produto")
public class ProdutoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private BigDecimal preco;
    private Integer quatidadeEtoque;

    //Faz sentido poder acessar uma lista de itens no meu Produto?
//    @OneToMany(mappedBy = "id.produto")
//    private Set<ItemPedido> itens = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;

}
