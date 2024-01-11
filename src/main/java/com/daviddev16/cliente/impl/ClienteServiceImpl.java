package com.daviddev16.cliente.impl;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.ClienteRepository;
import com.daviddev16.cliente.ClienteService;
import com.daviddev16.cliente.exception.ClienteNaoEncontradoException;
import com.daviddev16.core.Defaults;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private Cliente internalLocalizarClientePorId(Integer clienteId) {
        return clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }

    @Override
    public void removerClientePorId(Integer clienteId) {
        Cliente clienteLocalizado = internalLocalizarClientePorId(clienteId);
        clienteRepository.delete(clienteLocalizado);
    }

    @Override
    public Cliente atualizarCliente(Integer clienteId, Cliente atualizacaoCliente) {
        Cliente clienteLocalizado = internalLocalizarClientePorId(clienteId);
        atualizacaoCliente.setId(clienteLocalizado.getId());
        return clienteRepository.save(atualizacaoCliente);
    }

    @Override
    public List<Cliente> localizarClientePorObjetoDeFiltro(Cliente exemploCliente) {

        final Example<Cliente> exampleParaQuery = Defaults
                .criarExampleSimplesGenericoPara(exemploCliente);

        return clienteRepository.findAll(exampleParaQuery);
    }

    @Override
    public Cliente localizarClientePorId(Integer clienteId) {
        return internalLocalizarClientePorId(clienteId);
    }

    @Override
    public List<Cliente> obterTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente incluirNovoCliente(Cliente novoCliente) {
        return clienteRepository.save(novoCliente);
    }
}
