package br.com.jovemtech.productordermanager.controller.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.usecase.empresa.BuscarEmpresaPorIdUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class BuscarEmpresaPorIdController {

    private final BuscarEmpresaPorIdUC buscarEmpresaPorIdUC;

    @Operation(summary = "EndPoint de Empresa", description = "Requisição para buscar Empresa por ID", tags = {"Buscar Empresa Por ID"}, operationId = "buscarEmpresaId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaGetDTO> buscarEmpresaPorId(@PathVariable Long id) {
        EmpresaGetDTO dto = buscarEmpresaPorIdUC.execute(id);

        dto.add(linkTo(methodOn(BuscarEmpresasController.class).buscarEmpresas()).withRel("Lista de empresas"));

        return ResponseEntity.ok().body(dto);
    }
}
