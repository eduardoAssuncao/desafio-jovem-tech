package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmpresaDTO {

    private String nomeFantasia;
    private String endereco;
    private String email;
    //@JsonIgnore
    //private Set<PedidoDTO> pedidos = new LinkedHashSet<>();
    //@JsonIgnore
    //private Set<ClienteDTO> clientes = new LinkedHashSet<>();
    //@JsonIgnore
    //private Set<ProdutoDTO> produtos = new LinkedHashSet<>();

    public EmpresaDTO(String nomeFantasia, String endereco, String email) {
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.email = email;
    }

    public EmpresaDTO(EmpresaSchema empresa) {
        this.nomeFantasia = empresa.getNomeFantasia();
        this.endereco = empresa.getEndereco();
        this.email = empresa.getEmail();
        //this.pedidos.addAll(empresa.getPedidos().stream().map(PedidoDTO::new).toList());
        //this.clientes.addAll(empresa.getClientes().stream().map(ClienteDTO::new).toList());
        //this.produtos.addAll(empresa.getProdutos().stream().map(ProdutoDTO::new).toList());
    }
}
