package br.com.jovemtech.productordermanager.controller.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.cliente.RealizarPagamentoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class RealizarPagamentoController {

    private final RealizarPagamentoUC realizarPagamentoUC;

    @Operation(summary = "EndPoint de Cliente", description = "Requisição para realizar pagamento do Pedido", tags = {"Realizar Pagamento do Pedido"}, operationId = "realizarPagamento")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json"))})
    @PutMapping("/pedido/{idCliente}/{idPedido}")
    public ResponseEntity<PedidoGetDTO> realizarPagamento(@PathVariable Long idCliente, @PathVariable Long idPedido){
        PedidoGetDTO pedido = realizarPagamentoUC.execute(idCliente, idPedido);
        return ResponseEntity.ok(pedido);
    }
}
