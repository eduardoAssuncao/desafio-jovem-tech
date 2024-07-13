package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private String nome;
    private String email;
    private String cpf;
    //@JsonIgnore
    //private EmpresaDTO empresa;
    //@JsonIgnore
    //private Set<PedidoDTO> pedidos = new LinkedHashSet<>();

    public ClienteDTO(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public ClienteDTO(ClienteSchema cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
      //  this.empresa = new EmpresaDTO(cliente.getEmpresa());
        //this.pedidos.addAll(cliente.getPedidos().stream().map(PedidoDTO::new).toList());
    }
}
