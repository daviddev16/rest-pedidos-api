package com.daviddev16.pedido.dtos.response;

import java.math.BigDecimal;

public class ResponseItemPedidoDTO {

    private String descricaoProduto;
    private BigDecimal valorUnitario;
    private Integer quantidadeItem;

    public ResponseItemPedidoDTO() {}

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(Integer quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }
}
