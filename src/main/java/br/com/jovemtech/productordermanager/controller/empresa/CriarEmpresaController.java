package br.com.jovemtech.productordermanager.controller.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.usecase.empresa.CriarEmpresaUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class CriarEmpresaController {

    private final CriarEmpresaUC criarEmpresaUC;

    @Operation(summary = "EndPoint de Empresa", description = "Requisição para realizar a criação de uma Empresa", tags = {"Criar Empresa"}, operationId = "criarEmpresa", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = EmpresaDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PostMapping
    public ResponseEntity<EmpresaGetDTO> criarEmpresa(@RequestBody EmpresaDTO dto) {
        EmpresaGetDTO empresa = criarEmpresaUC.execute(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(empresa);
    }
}
