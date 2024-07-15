package br.com.jovemtech.productordermanager.dto;

import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaDTO {

    @NotBlank(message = "O nome fantasia não pode estar em branco")
    private String nomeFantasia;

    @NotBlank(message = "O endereço não pode estar em branco")
    private String endereco;

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "E-mail deve ser válido")
    private String email;

    public EmpresaDTO(String nomeFantasia, String endereco, String email) {
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.email = email;
    }

    public EmpresaDTO(EmpresaSchema empresa) {
        this.nomeFantasia = empresa.getNomeFantasia();
        this.endereco = empresa.getEndereco();
        this.email = empresa.getEmail();
    }
}
