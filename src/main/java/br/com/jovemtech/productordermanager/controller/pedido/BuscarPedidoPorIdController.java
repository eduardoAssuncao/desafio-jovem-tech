package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.pedido.BuscarPedidoPorIdUC;
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
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class BuscarPedidoPorIdController {

    private final BuscarPedidoPorIdUC buscarPedidoPorIdUC;

    @Operation(summary = "EndPoint de Pedido", description = "Requisição para buscar Pedido por ID", tags = {"Buscar Pedido Por ID"}, operationId = "buscarPedidoId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping("/{id}")
    public ResponseEntity<PedidoGetDTO> buscarPedidoPorId(@PathVariable Long id) {
        PedidoGetDTO dto = buscarPedidoPorIdUC.execute(id);
        return ResponseEntity.ok().body(dto);
    }
}
