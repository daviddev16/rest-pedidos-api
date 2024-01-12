package com.daviddev16.pedido.transformers;

import com.daviddev16.pedido.Pedido;
import com.daviddev16.pedido.dtos.response.ResponseItemPedidoDTO;
import com.daviddev16.pedido.dtos.response.ResponsePedidoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class PedidoResponseTransformer {

    public ResponsePedidoDTO transformarPedidoEmResponseDTO(final Pedido pedido) {

        final ResponsePedidoDTO.ResponsePedidoDTOBuilder pedidoResponseDtoBuilder = ResponsePedidoDTO.builder();

        pedidoResponseDtoBuilder
                .idPedido(pedido.getId())
                .nomeCliente(pedido.getCliente().getNome())
                .cpf(pedido.getCliente().getCpf())
                .valorTotalPedido(pedido.getValorTotal());

        Set<ResponseItemPedidoDTO> itemPedidoDtos = pedido.getItens()
                .stream()
                .map(itemPedido ->
                {
                    ResponseItemPedidoDTO responseItemPedidoDto = new ResponseItemPedidoDTO();
                    responseItemPedidoDto.setQuantidadeItem(itemPedido.getQuantidade());
                    responseItemPedidoDto.setValorUnitario(itemPedido.getProduto().getValor());
                    responseItemPedidoDto.setDescricaoProduto(itemPedido.getProduto().getDescricao());
                    return responseItemPedidoDto;
                })
                .collect(Collectors.toSet());

        pedidoResponseDtoBuilder.itemsPedido(itemPedidoDtos);
        pedidoResponseDtoBuilder.statusPedido(pedido.getStatusPedido().name());

        return pedidoResponseDtoBuilder.build();
    }

    public List<ResponsePedidoDTO> transformarPedidosEmResponseDTOs(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::transformarPedidoEmResponseDTO)
                .collect(Collectors.toList());
    }

}
