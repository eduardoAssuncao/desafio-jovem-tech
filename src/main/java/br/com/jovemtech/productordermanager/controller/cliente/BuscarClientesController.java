package br.com.jovemtech.productordermanager.controller.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteGetDTO;
import br.com.jovemtech.productordermanager.usecase.cliente.BuscarClientesUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class BuscarClientesController {

    private final BuscarClientesUC buscarClientesUC;

    @Operation(summary = "EndPoint de Cliente", description = "Requisição para buscar todos os Clientes", tags = {"Buscar Clientes"}, operationId = "buscarClientes")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping
    public ResponseEntity<List<ClienteGetDTO>> buscarClientes(){
        List<ClienteGetDTO> dtos = buscarClientesUC.execute();
        return ResponseEntity.ok(dtos);
    }
}
