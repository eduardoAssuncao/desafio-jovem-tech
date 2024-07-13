package br.com.jovemtech.productordermanager.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private String password;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<PedidoSchema> pedidos = new LinkedHashSet<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteSchema that = (ClienteSchema) o;
        return id != null && id.equals(that.id); // Use apenas o ID para comparação
    }

    @Override
    public int hashCode() {
        return 31; // Valor fixo para hashCode, ou pode usar um valor baseado no ID se preferir
    }

    @Override
    public String toString() {
        return "ClienteSchema{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", empresaId=" + (empresa != null ? empresa.getId() : null) +
                '}';
    }
}
