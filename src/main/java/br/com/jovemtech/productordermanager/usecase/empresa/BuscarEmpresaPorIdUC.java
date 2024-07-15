package br.com.jovemtech.productordermanager.usecase.empresa;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuscarEmpresaPorIdUC {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public EmpresaGetDTO execute(Long id){
        EmpresaSchema empresa = empresaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Erro ao buscar produto com o id " + id));
        return modelMapper.map(empresa, EmpresaGetDTO.class);
    }
}
