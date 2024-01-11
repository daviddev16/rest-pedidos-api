package com.daviddev16.pedido.dtos.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponsePedidoDTO {

    private Integer idPedido;
    private String nomeCliente;
    private String cpf;
    private BigDecimal valorTotalPedido;
    private Set<ResponseItemPedidoDTO> itemsPedido;
    private String statusPedido;

}
