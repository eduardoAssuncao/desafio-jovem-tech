package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoverProdutoPorIdUC {

    private final ProdutoRepository produtoRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void execute(Long id){
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundException("Erro ao buscar produto com o id " + id);
        }
        try{
            produtoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
