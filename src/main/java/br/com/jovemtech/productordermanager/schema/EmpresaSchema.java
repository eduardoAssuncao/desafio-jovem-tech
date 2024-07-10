package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
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

    @OneToMany(mappedBy = "empresa")
    private Set<PedidoSchema> pedidos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<ClienteSchema> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<ProdutoSchema> produtos = new LinkedHashSet<>();
}
