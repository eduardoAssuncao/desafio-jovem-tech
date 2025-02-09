package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarProdutoUC {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ProdutoGetDTO execute(ProdutoDTO dto){
        ProdutoSchema schema = modelMapper.map(dto, ProdutoSchema.class);
        schema = produtoRepository.save(schema);
        return modelMapper.map(schema, ProdutoGetDTO.class);
    }
}
