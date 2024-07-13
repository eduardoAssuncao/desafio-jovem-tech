package br.com.jovemtech.productordermanager.infrastructure.repository;

import br.com.jovemtech.productordermanager.schema.ItemPedidoPK;
import br.com.jovemtech.productordermanager.schema.ItemPedidoSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoSchema, ItemPedidoPK> {
}
