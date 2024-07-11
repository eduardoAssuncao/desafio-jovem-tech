package br.com.jovemtech.productordermanager.usecase.produto;

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
        ProdutoSchema produto = modelMapper.map(dto, ProdutoSchema.class);
        produto = produtoRepository.save(produto);
        return modelMapper.map(produto, ProdutoGetDTO.class);
    }
}
