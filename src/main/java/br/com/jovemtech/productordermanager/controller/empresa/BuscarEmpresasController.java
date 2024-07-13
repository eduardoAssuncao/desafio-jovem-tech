package br.com.jovemtech.productordermanager.controller.empresa;

import br.com.jovemtech.productordermanager.dto.EmpresaGetDTO;
import br.com.jovemtech.productordermanager.usecase.empresa.BuscarEmpresasUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class BuscarEmpresasController {

    private final BuscarEmpresasUC buscarEmpresasUC;

    @Operation(summary = "EndPoint de Empresa", description = "Requisição para buscar todos as Empresas", tags = {"Buscar Empresa"}, operationId = "buscarEmpresas")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaGetDTO.class))),@ApiResponse(responseCode = "400", description = "Formato da requisição incorreto.", content = @Content(schema = @Schema()))})
    @GetMapping
    public ResponseEntity<List<EmpresaGetDTO>> buscarEmpresas() {
        List<EmpresaGetDTO> dtos = buscarEmpresasUC.execute();

        if (!dtos.isEmpty()) {
            for (EmpresaGetDTO empresa : dtos) {
                Long id = empresa.getId();
                empresa.add(linkTo(methodOn(BuscarEmpresaPorIdController.class)
                        .buscarEmpresaPorId(id)).withSelfRel());
            }
        }

        return ResponseEntity.ok(dtos);
    }
}
