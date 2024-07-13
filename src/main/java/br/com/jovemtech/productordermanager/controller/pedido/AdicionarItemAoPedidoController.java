package br.com.jovemtech.productordermanager.controller.pedido;

import br.com.jovemtech.productordermanager.dto.ItemPedidoDTO;
import br.com.jovemtech.productordermanager.dto.ItemPedidoGetDTO;
import br.com.jovemtech.productordermanager.dto.PedidoDTO;
import br.com.jovemtech.productordermanager.dto.PedidoGetDTO;
import br.com.jovemtech.productordermanager.usecase.pedido.AdicionarItemAoPedidoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class AdicionarItemAoPedidoController {

    private final AdicionarItemAoPedidoUC adicionarItemAoPedidoUC;

    @PutMapping("/{idDoPedido}/{idDoProduto}")
    public ResponseEntity<ItemPedidoGetDTO> adicionarItemAoPedido(@PathVariable Long idDoPedido,
                                                                  @PathVariable Long idDoProduto,
                                                                  @RequestBody ItemPedidoDTO dto){

        var pedido = adicionarItemAoPedidoUC.execute(idDoPedido, idDoProduto, dto);
        return ResponseEntity.ok().body(pedido);
    }
}
