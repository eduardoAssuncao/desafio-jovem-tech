package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.usecase.produto.AtualizarProdutoPorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class AtualizarProdutoPorIdController {

    private final AtualizarProdutoPorIdUC atualizarProduto;

    @Operation(summary = "EndPoint de Produto", description = "Requisição para atualizar um Produto por ID", tags = {"Atualizar Produto Por ID"}, operationId = "atualizarProduto", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ProdutoDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        dto = atualizarProduto.execute(id, dto);
        return ResponseEntity.ok(dto);
    }
}
