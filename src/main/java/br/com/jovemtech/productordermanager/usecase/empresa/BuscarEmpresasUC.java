package br.com.jovemtech.productordermanager.usecase.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarEmpresasUC {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<EmpresaGetDTO> execute(){
        try{
            List<EmpresaSchema> empresas = empresaRepository.findAll();
            return empresas.stream().map(empresa -> modelMapper.map(empresa, EmpresaGetDTO.class)).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
