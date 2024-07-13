package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClienteGetDTO extends RepresentationModel<ClienteGetDTO> {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private EmpresaDTO empresa;
    private Set<PedidoDTO> pedidos = new LinkedHashSet<>();

    public ClienteGetDTO(ClienteSchema cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.empresa = new EmpresaDTO(cliente.getEmpresa());
        this.pedidos.addAll(cliente.getPedidos().stream().map(PedidoDTO::new).toList());
    }
}
