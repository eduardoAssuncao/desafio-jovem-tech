package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class ClienteDTO {

    private String nome;
    private String email;
    private String cpf;
    private EmpresaDTO empresa;
    private Set<PedidoDTO> pedidos = new LinkedHashSet<>();

    public ClienteDTO(String nome, String email, String cpf, EmpresaDTO empresa) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.empresa = empresa;
    }

    public ClienteDTO(ClienteSchema cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.empresa = new EmpresaDTO(cliente.getEmpresa());
        this.pedidos.addAll(cliente.getPedidos().stream().map(PedidoDTO::new).toList());
    }
}
