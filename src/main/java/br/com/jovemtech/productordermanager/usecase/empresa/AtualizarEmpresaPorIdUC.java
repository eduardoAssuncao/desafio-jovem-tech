package br.com.jovemtech.productordermanager.usecase.empresa;

import br.com.jovemtech.productordermanager.config.exception.ResourceNotFoundException;
import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarEmpresaPorIdUC {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public EmpresaDTO execute(Long id, EmpresaDTO dto) {
        if(!empresaRepository.existsById(id)){
            throw new ResourceNotFoundException("Erro ao buscar empresa com o id " + id);
        }
        try{
            EmpresaSchema empresa = empresaRepository.getReferenceById(id);
            modelMapper.map(empresa, dto);
            empresaRepository.save(empresa);
            return modelMapper.map(empresa, EmpresaDTO.class);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
