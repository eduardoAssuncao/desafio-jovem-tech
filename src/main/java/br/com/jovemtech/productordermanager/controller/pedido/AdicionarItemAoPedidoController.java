package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.*;
import br.com.jovemtech.productordermanager.usecase.pedido.AdicionarItemAoPedidoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class AdicionarItemAoPedidoController {

    private final AdicionarItemAoPedidoUC adicionarItemAoPedidoUC;

    @Operation(summary = "EndPoint de Pedido", description = "Requisição para adicionar item ao pedido", tags = {"Adicionar item ao pedido"}, operationId = "adicionarItem", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemPedidoDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PutMapping("/{idDoPedido}/{idDoProduto}")
    public ResponseEntity<ItemPedidoGetDTO> adicionarItemAoPedido(@PathVariable Long idDoPedido,
                                                                  @PathVariable Long idDoProduto,
                                                                  @Valid @RequestBody ItemPedidoDTO dto){

        var pedido = adicionarItemAoPedidoUC.execute(idDoPedido, idDoProduto, dto);
        return ResponseEntity.ok().body(pedido);
    }
}
