package com.daviddev16.pedido;

import com.daviddev16.pedido.dtos.request.RequestAtualizaStatusPedidoDTO;
import com.daviddev16.pedido.dtos.request.RequestPedidoDTO;
import com.daviddev16.pedido.dtos.response.ResponsePedidoDTO;
import com.daviddev16.pedido.transformers.PedidoResponseTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoResponseTransformer pedidoTransformer;

    public PedidoController(PedidoService pedidoService,
                            PedidoResponseTransformer pedidoTransformer)
    {
        this.pedidoService = pedidoService;
        this.pedidoTransformer = pedidoTransformer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarNovoPedido( @Valid @RequestBody RequestPedidoDTO requestPedidoDto )
    {
        return pedidoService.criarNovoPedido(requestPedidoDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponsePedidoDTO> obterTodosPedidos()
    {
        return pedidoTransformer
                .transformarPedidosEmResponseDTOs( pedidoService.obterTodosPedidos() );
    }

    @GetMapping(value = "/{pedidoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePedidoDTO localizarPedidoPorId( @PathVariable Integer pedidoId )
    {
         return pedidoTransformer
                 .transformarPedidoEmResponseDTO( pedidoService.obterPedidoCompleto(pedidoId) );
    }

    @PatchMapping(value = "/{pedidoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarStatusPedido( @PathVariable("pedidoId") Integer pedidoId,
                                      @Valid @RequestBody RequestAtualizaStatusPedidoDTO atualizaStatusPedidoDto )
    {
        pedidoService.atualizaStatusPedido(pedidoId, StatusPedido
                .converterParaStatusPedido(atualizaStatusPedidoDto.getNovoStatusPedido()));
    }

}
