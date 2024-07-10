package br.com.jovemtech.productordermanager.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_pedido")
public class PedidoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataPedido;
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClienteSchema cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;
}
