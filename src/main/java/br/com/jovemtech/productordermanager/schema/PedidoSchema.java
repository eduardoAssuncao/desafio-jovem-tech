package br.com.jovemtech.productordermanager.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
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
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private ClienteSchema cliente;

    @OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ItemPedidoSchema> itens = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoSchema that = (PedidoSchema) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PedidoSchema{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", status=" + status +
                ", clienteId=" + (cliente != null ? cliente.getId() : null) +
                '}';
    }
}
