package br.com.jovemtech.productordermanager.infrastructure.repository;

import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaSchema, Long> {
}
