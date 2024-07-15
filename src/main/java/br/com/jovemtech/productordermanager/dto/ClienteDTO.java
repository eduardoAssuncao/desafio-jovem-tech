package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "E-mail deve ser válido")
    private String email;

    @NotBlank(message = "O CPF não pode estar em branco")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato xxx.xxx.xxx-xx")
    private String cpf;

    public ClienteDTO(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public ClienteDTO(ClienteSchema cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
    }
}
