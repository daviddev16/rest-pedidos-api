package com.daviddev16.pedido.dtos.request;

public class RequestAtualizaStatusPedidoDTO {

    private String novoStatusPedido;

    public RequestAtualizaStatusPedidoDTO() {}

    public String getNovoStatusPedido() {
        return novoStatusPedido;
    }

    public void setNovoStatusPedido(String novoStatusPedido) {
        this.novoStatusPedido = novoStatusPedido;
    }
}
