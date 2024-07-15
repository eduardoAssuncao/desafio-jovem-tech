package br.com.jovemtech.productordermanager.controller.empresa;

import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.empresa.FinalizarPedidoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class FinalizarPedidoController {

    private final FinalizarPedidoUC finalizarPedidoUC;

    @Operation(summary = "EndPoint de Empresa", description = "Requisição para finalizar um pedido por ID", tags = {"Finalizar Pedido Por ID"}, operationId = "finalizarPedido")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida")})
    @PutMapping("/finalizar/{idPedido}")
    public ResponseEntity<PedidoGetDTO> finalizarPedido(@PathVariable Long idPedido) {
        PedidoGetDTO pedido = finalizarPedidoUC.execute(idPedido);
        return ResponseEntity.ok(pedido);
    }
}
