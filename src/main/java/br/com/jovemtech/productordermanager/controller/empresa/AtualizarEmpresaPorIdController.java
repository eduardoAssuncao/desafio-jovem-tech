package br.com.jovemtech.productordermanager.controller.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaDTO;
import br.com.jovemtech.productordermanager.usecase.empresa.AtualizarEmpresaPorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class AtualizarEmpresaPorIdController {

    private final AtualizarEmpresaPorIdUC atualizarEmpresaPorIdUC;

    @Operation(summary = "EndPoint de Empresa", description = "Requisição para atualizar uma Empresa por ID", tags = {"Atualizar Empresa Por ID"}, operationId = "atualizarEmpresa", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = EmpresaDTO.class))))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> atualizarEmpresaPorId(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
        dto = atualizarEmpresaPorIdUC.execute(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
