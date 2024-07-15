package br.com.jovemtech.productordermanager.usecase.produto;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarProdutoUC {

    private final ProdutoRepository produtoRepository;
    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ProdutoGetDTO execute(ProdutoDTO dto, Long idEmpresa){
        EmpresaSchema empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));
        ProdutoSchema produto = modelMapper.map(dto, ProdutoSchema.class);
        produto.setEmpresa(empresa);
        ProdutoSchema produtoSalvo = produtoRepository.save(produto);
        empresa.getProdutos().add(produtoSalvo);
        empresaRepository.save(empresa);
        return modelMapper.map(produtoSalvo, ProdutoGetDTO.class);

    }
}
