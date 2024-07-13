package br.com.jovemtech.productordermanager.usecase.cliente;

import br.com.jovemtech.productordermanager.dto.ClienteDTO;
import br.com.jovemtech.productordermanager.dto.ClienteGetDTO;
import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.infrastructure.repository.ClienteRepository;
import br.com.jovemtech.productordermanager.infrastructure.repository.EmpresaRepository;
import br.com.jovemtech.productordermanager.schema.ClienteSchema;
import br.com.jovemtech.productordermanager.schema.EmpresaSchema;
import br.com.jovemtech.productordermanager.usecase.empresa.AtualizarEmpresaPorIdUC;
import br.com.jovemtech.productordermanager.usecase.empresa.BuscarEmpresaPorIdUC;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarClienteUC {

    private final ClienteRepository clienteRepository;
    private final EmpresaRepository empresaRepository;
    private final AtualizarEmpresaPorIdUC atualizarEmpresaPorId;
    private final BuscarEmpresaPorIdUC buscarEmpresaPorIdUC;
    private final AtualizarEmpresaPorIdUC atualizarEmpresaPorIdUC;
    private final ModelMapper modelMapper;

    @Transactional
    public ClienteGetDTO execute(ClienteDTO dto, Long idEmpresa) {
        EmpresaSchema empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        ClienteSchema cliente = modelMapper.map(dto, ClienteSchema.class);
        cliente.setEmpresa(empresa);
        ClienteSchema savedCliente = clienteRepository.save(cliente);
        empresa.getClientes().add(savedCliente);

        empresaRepository.save(empresa); // Atualiza a empresa com o novo cliente

        return modelMapper.map(savedCliente, ClienteGetDTO.class);
    }
}
