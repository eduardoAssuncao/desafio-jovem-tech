package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.pedido.RemoverPedidoPorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class RemoverPedidoPorIdController {

    private final RemoverPedidoPorIdUC removerPedidoPorIdUC;

    @Operation(summary = "EndPoint de Pedido", description = "Requisição para remover Pedido por ID", tags = {"Deletar pedido por ID"}, operationId = "deletarPedido")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida")})
    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoGetDTO> removerPedidoPorId(@PathVariable Long id) {

        removerPedidoPorIdUC.execute(id);
        return ResponseEntity.noContent().build();
    }
}
