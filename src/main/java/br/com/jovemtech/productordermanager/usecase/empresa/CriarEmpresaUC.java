package br.com.jovemtech.productordermanager.usecase.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarEmpresaUC {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public EmpresaGetDTO execute(EmpresaDTO dto) {
        EmpresaSchema empresa = empresaRepository.save(modelMapper.map(dto, EmpresaSchema.class));
        return modelMapper.map(empresa, EmpresaGetDTO.class);
    }
}
