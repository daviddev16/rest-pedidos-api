package com.daviddev16.pedido.transformer;

import com.daviddev16.pedido.ItemPedido;
import com.daviddev16.pedido.Pedido;
import com.daviddev16.pedido.dto.request.RequestItemPedidoDTO;
import com.daviddev16.pedido.exception.PedidoTotalItensInsuficientesException;
import com.daviddev16.produto.Produto;
import com.daviddev16.produto.ProdutoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PedidoRequestTransformer {

    private final ProdutoService produtoService;

    public PedidoRequestTransformer(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public Set<ItemPedido> transformarItemPedidoDTOemItemPedidos(final Pedido pedido,
                                                                  List<RequestItemPedidoDTO> requestItemPedidoDtos)
    {
        if (requestItemPedidoDtos.isEmpty())
            throw new PedidoTotalItensInsuficientesException(1);

        return requestItemPedidoDtos
                .stream()
                .map(requestItemPedidoDto ->
                {
                    final Produto produtoItem = produtoService
                            .localizarProdutoPorId(requestItemPedidoDto.getIdProduto());

                    return ItemPedido.builder()
                            .quantidade(requestItemPedidoDto.getQuantidade())
                            .produto(produtoItem)
                            .pedido(pedido)
                            .build();

                })
                .collect(Collectors.toSet());
    }

}
