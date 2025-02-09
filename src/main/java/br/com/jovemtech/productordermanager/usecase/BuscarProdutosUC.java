package br.com.jovemtech.productordermanager.usecase;

import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarProdutosUC {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ProdutoGetDTO> execute(){
        List<ProdutoSchema> produtos = produtoRepository.findAll();
        return produtos.stream().map(produtoSchema ->
                modelMapper.map(produtoSchema, ProdutoGetDTO.class)).collect(Collectors.toList());
    }
}
