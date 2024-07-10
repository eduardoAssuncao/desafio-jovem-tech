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
@Table(name = "tb_cliente")
public class ClienteSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private Set<PedidoSchema> pedidos = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;
}
