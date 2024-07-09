package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.usecase.CriarProdutoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class CriarProdutoController {

    private final CriarProdutoUC criarProduto;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO dto) {
        dto = criarProduto.execute(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
