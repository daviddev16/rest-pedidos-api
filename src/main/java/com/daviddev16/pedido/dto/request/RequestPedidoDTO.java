package com.daviddev16.pedido.dto.request;


import com.daviddev16.core.annotation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/* LOMBOK */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestPedidoDTO {

    @NotNull(message = "{msg.pedido.codigo-cliente.obrigatorio}")
    private Integer idCliente;

    @NotNull(message = "{msg.pedido.valor-total.obrigatorio}")
    private BigDecimal valorTotalPedido;

    @NotEmptyList(message = "{msg.pedido.quantidade-de-itens.obrigatorio}")
    private List<RequestItemPedidoDTO> itensPedido;

}
