package br.com.jovemtech.productordermanager.usecase.produto;

import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoverProdutosUC {

    private final ProdutoRepository produtoRepository;

    public void execute(){
        try{
            produtoRepository.deleteAll();
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
