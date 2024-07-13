package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.dto.PedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.pedido.CriarPedidoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class CriarPedidoController {

    private final CriarPedidoUC criarPedidoUC;

    @Operation(summary = "EndPoint de Pedido", description = "Requisição para realizar a criação de um Pedido", tags = {"Criar Pedido"}, operationId = "criarPedido", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = PedidoDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PostMapping
    public ResponseEntity<PedidoGetDTO> criarPedido(@RequestBody PedidoDTO dto) {
        PedidoGetDTO pedido = criarPedidoUC.execute(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

}
