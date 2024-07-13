package br.com.jovemtech.productordermanager.controller.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.usecase.empresa.RemoverEmpresaPorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class RemoverEmpresaPorIdController {

    private final RemoverEmpresaPorIdUC removerEmpresaPorIdUC;

    @Operation(summary = "EndPoint de Empresa", description = "Requisição para remover Empresa por ID", tags = {"Deletar Empresa"}, operationId = "deletarEmpresa")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Operação bem sucedida", content = @Content(mediaType = "application/json")),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDTO> removerEmpresaPorId(@PathVariable Long id) {
        removerEmpresaPorIdUC.execute(id);
        return ResponseEntity.noContent().build();
    }
}
