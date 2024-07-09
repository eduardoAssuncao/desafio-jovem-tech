package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarProdutoPorIdUC {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public ProdutoGetDTO execute(Long id){
        ProdutoSchema produtoSchema = produtoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Erro ao buscar produto com o id " + id));
        return modelMapper.map(produtoSchema, ProdutoGetDTO.class);
    }
}
