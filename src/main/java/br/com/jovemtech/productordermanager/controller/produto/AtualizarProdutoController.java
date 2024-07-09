package br.com.jovemtech.productordermanager.controller.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.usecase.AtualizarProdutoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class AtualizarProdutoController {

    private final AtualizarProdutoUC atualizarProduto;

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        dto = atualizarProduto.execute(id, dto);
        return ResponseEntity.ok(dto);
    }
}
