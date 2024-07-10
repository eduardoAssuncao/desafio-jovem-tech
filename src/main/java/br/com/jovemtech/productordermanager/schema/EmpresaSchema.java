package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
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
    private Set<PedidoSchema> pedidos = new HashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<ClienteSchema> clientes = new HashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<ProdutoSchema> produtos = new HashSet<>();
}
