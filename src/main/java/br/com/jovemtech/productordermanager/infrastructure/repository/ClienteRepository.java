package br.com.jovemtech.productordermanager.infrastructure.repository;

import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteSchema, Long> {

    Page<ClienteSchema> findAll(Pageable pageable);
}
