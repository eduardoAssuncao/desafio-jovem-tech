package br.com.jovemtech.productordermanager.schema;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.usecase.produto.CriarProdutoUC;
import br.com.jovemtech.productordermanager.usecase.produto.RemoverProdutoPorIdUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoverProdutoPorIdTest {

    @InjectMocks
    private CriarProdutoUC criarProdutoUC;

    @InjectMocks
    private RemoverProdutoPorIdUC removerProdutoPorIdUC;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private ModelMapper modelMapper;

    private ProdutoDTO produtoDTO;
    private EmpresaSchema empresaSchema;
    private ProdutoSchema produtoSchema;

    @BeforeEach
    void setUp() {
        produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("TV");
        produtoDTO.setDescricao("TV 4K");
        produtoDTO.setPreco(BigDecimal.valueOf(4000));
        produtoDTO.setQuantidadeEstoque(10);

        empresaSchema = new EmpresaSchema();
        empresaSchema.setId(1L);
        empresaSchema.setNomeFantasia("Mateus");
        empresaSchema.setEndereco("Cohama");
        empresaSchema.setEmail("mateus@gmail.com");

        produtoSchema = new ProdutoSchema();
        produtoSchema.setId(1L);
        produtoSchema.setNome("TV");
        produtoSchema.setDescricao("TV 4K");
        produtoSchema.setPreco(BigDecimal.valueOf(4000));
        produtoSchema.setQuatidadeEstoque(10);
        produtoSchema.setEmpresa(empresaSchema);
    }

    @Test
    void testCriarEDeletarProduto() {
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresaSchema));
        when(modelMapper.map(any(ProdutoDTO.class), eq(ProdutoSchema.class))).thenReturn(produtoSchema);
        when(produtoRepository.save(any(ProdutoSchema.class))).thenReturn(produtoSchema);
        when(modelMapper.map(any(ProdutoSchema.class), eq(ProdutoGetDTO.class))).thenReturn(new ProdutoGetDTO(produtoSchema));

        ProdutoGetDTO produto = criarProdutoUC.execute(produtoDTO, 1L);

        assertNotNull(produto);
        verify(produtoRepository, times(1)).save(any(ProdutoSchema.class));

        when(produtoRepository.existsById(produto.getId())).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(produto.getId());

        removerProdutoPorIdUC.execute(produto.getId());

        verify(produtoRepository, times(1)).existsById(produto.getId());
        verify(produtoRepository, times(1)).deleteById(produto.getId());
    }
}
