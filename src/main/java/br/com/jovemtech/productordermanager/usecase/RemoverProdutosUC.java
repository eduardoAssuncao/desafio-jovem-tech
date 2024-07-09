package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoverProdutosUC {

    private final ProdutoRepository produtoRepository;

    public void execute(){
        produtoRepository.deleteAll();
    }
}
