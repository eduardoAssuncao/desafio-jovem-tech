package br.com.jovemtech.productordermanager.schema;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.usecase.produto.CriarProdutoUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarProdutoTest {

    @InjectMocks
    private CriarProdutoUC criarProdutoUC;

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
        produtoSchema.setQuantidadeEstoque(10);
        produtoSchema.setEmpresa(empresaSchema);
    }

    @Test
    void testCriarProduto() {
        when(empresaRepository.findById(anyLong())).thenReturn(java.util.Optional.of(empresaSchema));
        when(modelMapper.map(any(ProdutoDTO.class), any(Class.class))).thenReturn(produtoSchema);
        when(produtoRepository.save(any(ProdutoSchema.class))).thenReturn(produtoSchema);
        when(modelMapper.map(any(ProdutoSchema.class), any(Class.class))).thenReturn(new ProdutoGetDTO(produtoSchema));

        ProdutoGetDTO result = criarProdutoUC.execute(produtoDTO, 1L);
        assertNotNull(result);
        System.out.println(result);
        assertEquals("TV", result.getNome());
        assertEquals(new BigDecimal(4000) , result.getPreco());
        assertEquals("TV 4K" , result.getDescricao());
        assertEquals(10 , result.getQuantidadeEstoque());
        assertEquals(new EmpresaDTO("Mateus", "Cohama", "mateus@gmail.com")
                , result.getEmpresa());
        verify(produtoRepository, times(1)).save(any(ProdutoSchema.class));
    }
}