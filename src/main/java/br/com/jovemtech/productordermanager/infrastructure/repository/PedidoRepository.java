package br.com.jovemtech.productordermanager.infrastructure.repository;

import br.com.jovemtech.productordermanager.schema.PedidoSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoSchema, Long>{

    @Query("SELECT obj FROM PedidoSchema obj JOIN FETCH obj.cliente")
    List<PedidoSchema> buscaTodos();
}
