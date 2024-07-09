package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarProdutoPorIdUC {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ProdutoDTO execute(Long id, ProdutoDTO dto) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundException("Erro ao buscar produto com o id " + id);
        }
        try{
            ProdutoSchema entity = produtoRepository.getReferenceById(id);
            modelMapper.map(dto, entity);
            entity = produtoRepository.save(entity);
            return new ProdutoDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
