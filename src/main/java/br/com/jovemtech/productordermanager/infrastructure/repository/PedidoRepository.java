package br.com.jovemtech.productordermanager.infrastructure.repository;

import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoSchema, Long>{
}
