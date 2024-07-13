package br.com.jovemtech.productordermanager.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoSchema that = (ProdutoSchema) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31; // Valor fixo para hashCode, ou pode usar um valor baseado no ID se preferir
    }
}
