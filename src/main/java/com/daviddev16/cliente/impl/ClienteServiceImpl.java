package com.daviddev16.cliente.impl;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.ClienteRepository;
import com.daviddev16.cliente.ClienteService;
import com.daviddev16.cliente.dto.request.RequestFiltroClienteDTO;
import com.daviddev16.cliente.exception.ClienteNaoEncontradoException;
import com.daviddev16.cliente.transformer.ClienteRequestTransformer;
import com.daviddev16.core.util.QueryDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteRequestTransformer clienteRequestTransformer;

    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              ClienteRequestTransformer clienteRequestTransformer)
    {
        this.clienteRepository = clienteRepository;
        this.clienteRequestTransformer = clienteRequestTransformer;
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
    public List<Cliente> localizarClientePorObjetoDeFiltro(RequestFiltroClienteDTO filtroClienteDTO) {

        final Cliente clienteModelo = clienteRequestTransformer
                .transformarFiltroClienteEmClienteModelo(filtroClienteDTO);

        final Example<Cliente> exampleParaQuery = QueryDefaults
                .criarExampleSimplesGenericoPara(clienteModelo);

        return clienteRepository.findAll(exampleParaQuery);
    }

    @Override
    public Cliente localizarClientePorId(Integer clienteId) {
        return internalLocalizarClientePorId(clienteId);
    }

    @Override
    public List<Cliente> localizarClientesPorParteNomeOrdenado(String nomeParte) {
        return clienteRepository
                .findByNomeContaining(nomeParte, Sort.by("nome"));
    }

    @Override
    public Page<Cliente> obterTodosClientes( int paginaInicial, int totalDeRegistros ) {
        final Pageable pageable = PageRequest.of(paginaInicial, totalDeRegistros);
        return clienteRepository.obterTodosClientesPaginado(pageable);
    }

    @Override
    public Cliente incluirNovoCliente(Cliente novoCliente) {
        return clienteRepository.save(novoCliente);
    }
}
