package com.daviddev16.pedido;

import com.daviddev16.core.RuntimeServiceException;

import java.util.Arrays;

import static java.lang.String.*;

public enum StatusPedido {

    REALIZADO,
    CANCELADO,
    ENTREGUE;


    public static StatusPedido converterParaStatusPedido(String status) {
        try {
            return StatusPedido.valueOf(status);
        } catch (Exception ignore) {
            throw new RuntimeServiceException(format("Status informado como \"%s\" não é válido. " +
                    "Tente: %s", status, Arrays.toString(values())));
        }
    }

}
