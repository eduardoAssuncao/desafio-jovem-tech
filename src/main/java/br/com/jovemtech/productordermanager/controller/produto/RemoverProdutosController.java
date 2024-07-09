package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.usecase.RemoverProdutosUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class RemoverProdutosController {

    private final RemoverProdutosUC removerProdutosUC;

    @Operation(summary = "EndPoint de Produto", description = "Requisição para remover todos os Produtos", tags = {"Deletar Produtos"}, operationId = "removerProduto")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Operação bem sucedida", content = @Content(mediaType = "application/json")),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @DeleteMapping
    private ResponseEntity<ProdutoDTO> removerProdutos() {
        removerProdutosUC.execute();
        return ResponseEntity.noContent().build();
    }
}
