package br.com.jovemtech.productordermanager.controller.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteGetDTO;
import br.com.jovemtech.productordermanager.usecase.cliente.BuscarClientePorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class BuscarClientePorIdController {

    private final BuscarClientePorIdUC buscarClientePorIdUC;

    @Operation(summary = "EndPoint de Cliente", description = "Requisição para buscar Cliente por ID", tags = {"Buscar Cliente Por ID"}, operationId = "buscarClienteId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping("/{id}")
    public ResponseEntity<ClienteGetDTO> buscarClientePorId(@PathVariable Long id) {
        ClienteGetDTO dto = buscarClientePorIdUC.execute(id);
        return ResponseEntity.ok().body(dto);
    }
}
