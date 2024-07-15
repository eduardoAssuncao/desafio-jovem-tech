package br.com.jovemtech.productordermanager.usecase.produto;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarProdutosUC {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Page<ProdutoGetDTO> execute(Pageable pageable){
        try{
            Page<ProdutoSchema> produtos = produtoRepository.findAll(pageable);
            return produtos.map(produto ->
                    modelMapper.map(produto, ProdutoGetDTO.class));

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
