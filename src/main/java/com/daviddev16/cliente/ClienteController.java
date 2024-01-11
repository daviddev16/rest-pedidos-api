package com.daviddev16.cliente;

import com.daviddev16.cliente.impl.ClienteServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente localizarClientePorId(@PathVariable("clienteId") Integer clienteId)
    {
        return clienteService.localizarClientePorId(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente incluirNovoCliente(@RequestBody Cliente novoCliente)
    {
        return clienteService.incluirNovoCliente(novoCliente);
    }

    @DeleteMapping(value = "/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerClientePorId(@PathVariable("clienteId") Integer clienteId)
    {
        clienteService.removerClientePorId(clienteId);
    }

    @PutMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizarCliente(@PathVariable("clienteId") Integer clienteId,
                                    @RequestBody Cliente atualizacaoCliente )
    {
        return clienteService.atualizarCliente(clienteId, atualizacaoCliente);
    }

    @GetMapping("/pesquisaAvancada")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> filtroAvancadoCliente(@RequestParam Cliente exemploCliente)
    {
        return clienteService.localizarClientePorObjetoDeFiltro(exemploCliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> localizarTodosClientes()
    {
        return clienteService.obterTodosClientes();
    }
}
