package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
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
        ProdutoSchema entity = produtoRepository.getReferenceById(id);
        modelMapper.map(dto, entity);
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }
}
