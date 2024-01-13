package com.daviddev16.cliente.transformer;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.dto.request.RequestFiltroClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteRequestTransformer {

    /**
     * RequestFiltroClienteDTO previne a validação de Bean em "Cliente" quando passado os parâmetros
     * de query no endpoint.
     **/
    public Cliente transformarFiltroClienteEmClienteModelo(final RequestFiltroClienteDTO filtroClienteDTO)
    {
        return Cliente
                .builder()
                    .nome(filtroClienteDTO.getNome())
                    .cpf(filtroClienteDTO.getCpf())
                .build();
    }

}
