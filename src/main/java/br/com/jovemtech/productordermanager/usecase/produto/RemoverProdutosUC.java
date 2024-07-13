package br.com.jovemtech.productordermanager.usecase.produto;

import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverProdutosUC {

    private final ProdutoRepository produtoRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void execute(){
        try{
            produtoRepository.deleteAll();
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
