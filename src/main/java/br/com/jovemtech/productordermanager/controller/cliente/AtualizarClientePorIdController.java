package br.com.jovemtech.productordermanager.controller.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.usecase.cliente.AtualizarClientePorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class AtualizarClientePorIdController {

    private final AtualizarClientePorIdUC atualizarClientePorIdUC;

    @Operation(summary = "EndPoint de Cliente", description = "Requisição para atualizar um Cliente por ID", tags = {"Atualizar Cliente Por ID"}, operationId = "atualizarCliente", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ClienteDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarClientePorId(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        dto = atualizarClientePorIdUC.execute(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
