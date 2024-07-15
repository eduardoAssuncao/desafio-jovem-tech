package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.pedido.AtualizarItemDoPedidoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class AtualizarItemDoPedidoController {

    private final AtualizarItemDoPedidoUC atualizarItemDoPedidoUC;

    @Operation(summary = "EndPoint de Pedido", description = "Requisição para atualizar item do pedido", tags = {"Atualizar item do pedido"}, operationId = "atualizarItem", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemPedidoDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PutMapping("/{idPedido}/{idProduto}")
    public ResponseEntity<PedidoGetDTO> atualizarItemDoPedido(@PathVariable Long idPedido,
                                                              @PathVariable Long idProduto,
                                                              @RequestBody ItemPedidoDTO dto){

       PedidoGetDTO pedido = atualizarItemDoPedidoUC.execute(idPedido, idProduto, dto);
       return ResponseEntity.ok(pedido);
    }
}
