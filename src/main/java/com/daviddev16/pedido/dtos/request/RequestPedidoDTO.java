package com.daviddev16.pedido.dtos.request;


import java.math.BigDecimal;
import java.util.List;

public class RequestPedidoDTO {

    private Integer idCliente;
    private BigDecimal valorTotalPedido;
    private List<RequestItemPedidoDTO> itensPedido;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public List<RequestItemPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<RequestItemPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
