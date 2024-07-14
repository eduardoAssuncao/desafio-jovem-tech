package br.com.jovemtech.productordermanager.schema;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.dto.ProdutoGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import br.com.jovemtech.productordermanager.usecase.produto.BuscarProdutoPorIdUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BuscarProdutoPorIdTest {

    @InjectMocks
    private BuscarProdutoPorIdUC buscarProdutoPorIdUC;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ModelMapper modelMapper;

    private ProdutoSchema produtoSchema;

    private EmpresaSchema empresaSchema;

    @BeforeEach
    void setUp() {

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
        produtoSchema.setQuatidadeEtoque(10);
        produtoSchema.setEmpresa(empresaSchema);
    }

    @Test
    void testBuscarProdutoPorId() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produtoSchema));
        when(modelMapper.map(any(ProdutoSchema.class), any(Class.class))).thenReturn(new ProdutoGetDTO(produtoSchema));

        ProdutoGetDTO result = buscarProdutoPorIdUC.execute(1L);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(1L, result.getId());
        assertEquals("TV", result.getNome());
        assertEquals(new BigDecimal(4000) , result.getPreco());
        assertEquals("TV 4K" , result.getDescricao());
        assertEquals(10 , result.getQuatidadeEtoque());
        assertEquals(new EmpresaDTO("Mateus", "Cohama", "mateus@gmail.com")
                , result.getEmpresa());
    }
}
