package br.com.jovemtech.productordermanager.controller.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.usecase.cliente.RemoverClientesUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class RemoverClientesController {

    private final RemoverClientesUC removerClientesUC;

    @Operation(summary = "EndPoint de Cliente", description = "Requisição para remover todos os Clientes", tags = {"Deletar Clientes"}, operationId = "removerCliente")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Operação bem sucedida", content = @Content(mediaType = "application/json")),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @DeleteMapping
    public ResponseEntity<ClienteDTO> removerClientes() {
        removerClientesUC.execute();
        return ResponseEntity.noContent().build();
    }
}
