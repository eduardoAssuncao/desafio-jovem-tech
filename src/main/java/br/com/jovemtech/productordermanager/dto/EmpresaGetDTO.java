package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmpresaGetDTO extends RepresentationModel<EmpresaGetDTO> {

    private Long id;
    private String nomeFantasia;
    private String endereco;
    private String email;
    //private Set<PedidoDTO> pedidos = new LinkedHashSet<>();
    private Set<ClienteDTO> clientes = new LinkedHashSet<>();
    private Set<ProdutoDTO> produtos = new LinkedHashSet<>();

    public EmpresaGetDTO(EmpresaSchema empresa) {
        this.id = empresa.getId();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.endereco = empresa.getEndereco();
        this.email = empresa.getEmail();
       // this.pedidos.addAll(empresa.getPedidos().stream().map(PedidoDTO::new).toList());
        this.clientes.addAll(empresa.getClientes().stream().map(ClienteDTO::new).toList());
        this.produtos.addAll(empresa.getProdutos().stream().map(ProdutoDTO::new).toList());
    }
}
