package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.usecase.produto.CriarProdutoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class CriarProdutoController {

    private final CriarProdutoUC criarProdutoUC;

    @Operation(summary = "EndPoint de Produto", description = "Requisição para realizar a criação de um Produto", tags = {"Criar Produto"}, operationId = "criarProduto", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ProdutoDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PostMapping("/{idDaEmpresa}")
    public ResponseEntity<ProdutoGetDTO> criarProduto(@PathVariable Long idDaEmpresa, @RequestBody ProdutoDTO dto) {
        ProdutoGetDTO produto = criarProdutoUC.execute(dto, idDaEmpresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }
}
