package com.daviddev16.pedido;

import com.daviddev16.pedido.dto.request.RequestPedidoDTO;

import java.util.List;

public interface PedidoService {

    Pedido criarNovoPedido( RequestPedidoDTO  requestPedidoDTO );

    Pedido obterPedidoCompleto( Integer pedidoId );

    List<Pedido> obterTodosPedidos();

    void atualizaStatusPedido( Integer pedidoId, StatusPedido novoStatusPedido );

}
