package br.com.jovemtech.productordermanager.schema;

import br.com.jovemtech.productordermanager.infrastructure.repository.ProdutoRepository;
import br.com.jovemtech.productordermanager.usecase.produto.BuscarProdutosUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarProdutosTest {

    @InjectMocks
    private BuscarProdutosUC buscarTodosProdutosUC;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        List<ProdutoSchema> produtos = new ArrayList<>();

        EmpresaSchema empresaSchema1 = new EmpresaSchema();
        empresaSchema1.setId(1L);
        empresaSchema1.setNomeFantasia("Empresa 1");
        empresaSchema1.setEmail("empresa1@gmail.com");
        empresaSchema1.setEndereco("Rua A, 123");

        ProdutoSchema produto1 = new ProdutoSchema();
        produto1.setId(1L);
        produto1.setNome("TV LG");
        produto1.setDescricao("TV 4K");
        produto1.setPreco(BigDecimal.valueOf(5000));
        produto1.setQuantidadeEstoque(10);
        produto1.setEmpresa(empresaSchema1);

        ProdutoSchema produto2 = new ProdutoSchema();
        produto2.setId(2L);
        produto2.setNome("TV SAMSUNG");
        produto2.setDescricao("TV 8K");
        produto2.setPreco(BigDecimal.valueOf(8000));
        produto2.setQuantidadeEstoque(20);
        produto2.setEmpresa(empresaSchema1);

        produtos.add(produto1);
        produtos.add(produto2);

        when(produtoRepository.findAll()).thenReturn(produtos);


    }

    @Test
    void testBuscarTodosProdutos() {
        List<ProdutoSchema> produtosEncontrados = produtoRepository.findAll();

        assertEquals(2, produtosEncontrados.size(), "Deveria ter retornado 2 produto");
        System.out.println(produtosEncontrados);

        ProdutoSchema primeiroProduto = produtosEncontrados.get(0);
        assertEquals("TV LG", primeiroProduto.getNome(), "Nome do primeiro produto deve ser 'TV LG'");
    }
}
