package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_cliente")
public class ClienteSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private Set<PedidoSchema> pedidos = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;
}
