package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.ItemPedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.pedido.RemoverItemDoPedidoUC;
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
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class RemoverItemDoPedidoController {

    private final RemoverItemDoPedidoUC removerItemDoPedidoUC;

    @Operation(summary = "EndPoint de Pedido", description = "Requisição para remover item do pedido", tags = {"Remover item do pedido"}, operationId = "removerItem")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemPedidoDTO.class)))})
    @PutMapping("/remover/{idPedido}/{idProduto}")
    public ResponseEntity<ItemPedidoGetDTO> removerItemDoPedido(@PathVariable Long idPedido, @PathVariable Long idProduto) {
        ItemPedidoGetDTO itemPedido = removerItemDoPedidoUC.execute(idPedido, idProduto);
        return ResponseEntity.ok(itemPedido);
    }
}
