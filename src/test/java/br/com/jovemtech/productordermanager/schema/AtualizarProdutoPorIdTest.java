package br.com.jovemtech.productordermanager.schema;

import br.com.jovemtech.productordermanager.dto.ProdutoDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.usecase.produto.AtualizarProdutoPorIdUC;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtualizarProdutoPorIdTest {

    @InjectMocks
    private AtualizarProdutoPorIdUC atualizarProdutoPorIdUC;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ModelMapper modelMapper;

    private ProdutoDTO produtoDTO;
    private ProdutoSchema produtoSchema;

    @BeforeEach
    void setUp() {
        produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("TV LG");
        produtoDTO.setDescricao("TV 8K");
        produtoDTO.setPreco(BigDecimal.valueOf(8500));
        produtoDTO.setQuantidadeEstoque(10);

        produtoSchema = new ProdutoSchema();
        produtoSchema.setId(1L);
        produtoSchema.setNome("TV SAMSUNG");
        produtoSchema.setDescricao("TV 4K");
        produtoSchema.setPreco(BigDecimal.valueOf(5000));
        produtoSchema.setQuantidadeEstoque(10);
    }

    @Test
    void testAtualizarProdutoPorId() {
        when(produtoRepository.existsById(anyLong())).thenReturn(true);
        when(produtoRepository.getReferenceById(anyLong())).thenReturn(produtoSchema);

        doAnswer(invocation -> {
            ProdutoDTO source = invocation.getArgument(0);
            ProdutoSchema destination = invocation.getArgument(1);
            destination.setNome(source.getNome());
            destination.setDescricao(source.getDescricao());
            destination.setPreco(source.getPreco());
            destination.setQuantidadeEstoque(source.getQuantidadeEstoque());
            return destination;
        }).when(modelMapper).map(any(ProdutoDTO.class), any(ProdutoSchema.class));

        when(produtoRepository.save(any(ProdutoSchema.class))).thenReturn(produtoSchema);
        when(modelMapper.map(any(ProdutoSchema.class), eq(ProdutoDTO.class))).thenReturn(produtoDTO);

        System.out.println(produtoSchema);

        ProdutoDTO result = atualizarProdutoPorIdUC.execute(1L, produtoDTO);

        assertNotNull(result);
        System.out.println(result);
        assertEquals("TV LG", result.getNome());
        assertEquals(BigDecimal.valueOf(8500), result.getPreco());
        assertEquals(20 , result.getQuantidadeEstoque());
        verify(produtoRepository, times(1)).save(any(ProdutoSchema.class));
    }
}
