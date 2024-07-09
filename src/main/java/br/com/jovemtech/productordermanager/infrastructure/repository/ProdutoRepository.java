package br.com.jovemtech.productordermanager.infrastructure.repository;

import br.com.jovemtech.productordermanager.schema.ProdutoSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoSchema, Long> {
}
