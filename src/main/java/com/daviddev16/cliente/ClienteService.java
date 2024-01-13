package com.daviddev16.cliente;

import com.daviddev16.cliente.dto.request.RequestFiltroClienteDTO;

import java.util.List;

public interface ClienteService {

    Cliente atualizarCliente( Integer clienteId, Cliente atualizacaoCliente );

    void removerClientePorId( Integer clienteId );

    Cliente incluirNovoCliente( Cliente novoCliente );

    List<Cliente> localizarClientePorObjetoDeFiltro( RequestFiltroClienteDTO filtroClienteDTO );

    Cliente localizarClientePorId( Integer clienteId );

    List<Cliente> obterTodosClientes();

}
