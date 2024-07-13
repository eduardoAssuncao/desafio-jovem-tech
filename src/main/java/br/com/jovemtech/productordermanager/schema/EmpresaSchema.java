package br.com.jovemtech.productordermanager.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_empresa")
public class EmpresaSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeFantasia;
    private String endereco;
    private String email;

   // @OneToMany(mappedBy = "empresa")
   // private Set<PedidoSchema> pedidos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private Set<ClienteSchema> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<ProdutoSchema> produtos = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaSchema that = (EmpresaSchema) o;
        return id != null && id.equals(that.id); // Use apenas o ID para comparação
    }

    @Override
    public int hashCode() {
        return 31; // Valor fixo para hashCode, ou pode usar um valor baseado no ID se preferir
    }
}
