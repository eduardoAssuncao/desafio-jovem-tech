package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.usecase.produto.BuscarProdutosUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class BuscarProdutosController {

    private final BuscarProdutosUC buscarProdutosUC;

    @Operation(summary = "EndPoint de Produto", description = "Requisição para buscar todos os Produtos", tags = {"Buscar Produtos"}, operationId = "buscarProdutos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping
    public ResponseEntity<List<ProdutoGetDTO>> buscarProdutos(){
        List<ProdutoGetDTO> dtos = buscarProdutosUC.execute();

        if (!dtos.isEmpty()) {
            for (ProdutoGetDTO produto : dtos) {
                Long id = produto.getId();
                produto.add(linkTo(methodOn(BuscarProdutoPorIdController.class)
                        .buscarProdutoPorId(id)).withSelfRel());
            }
        }

        return ResponseEntity.ok(dtos);
    }
}
