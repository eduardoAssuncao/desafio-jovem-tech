package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.usecase.produto.BuscarProdutoPorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class BuscarProdutoPorIdController {

    private final BuscarProdutoPorIdUC buscarProdutoPorIdUC;

    @Operation(summary = "EndPoint de Produto", description = "Requisição para buscar Produto por ID", tags = {"Buscar Produto Por ID"}, operationId = "buscarProdutoId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoGetDTO> buscarProdutoPorId(@PathVariable Long id, @Parameter(hidden = true) Pageable pageable) {
        ProdutoGetDTO dto = buscarProdutoPorIdUC.execute(id);

        dto.add(linkTo(methodOn(BuscarProdutosController.class).buscarProdutos(pageable)).withRel("Lista de produtos"));

        return ResponseEntity.ok().body(dto);
    }
}
