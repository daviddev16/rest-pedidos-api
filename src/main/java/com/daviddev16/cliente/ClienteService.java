package com.daviddev16.cliente;

import java.util.List;

public interface ClienteService {

    Cliente atualizarCliente( Integer clienteId, Cliente atualizacaoCliente );

    void removerClientePorId( Integer clienteId );

    Cliente incluirNovoCliente( Cliente novoCliente );

    List<Cliente> localizarClientePorObjetoDeFiltro( Cliente exemploCliente );

    Cliente localizarClientePorId( Integer clienteId );

    List<Cliente> obterTodosClientes();

}
