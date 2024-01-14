package com.daviddev16.cliente;

import com.daviddev16.cliente.dto.request.RequestFiltroClienteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public Cliente localizarClientePorId( @PathVariable("clienteId") Integer clienteId )
    {
        return clienteService.localizarClientePorId(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente incluirNovoCliente( @Valid @RequestBody Cliente novoCliente )
    {
        return clienteService.incluirNovoCliente(novoCliente);
    }

    @DeleteMapping(value = "/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerClientePorId( @PathVariable("clienteId") Integer clienteId )
    {
        clienteService.removerClientePorId(clienteId);
    }

    @PutMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizarCliente( @PathVariable("clienteId") Integer clienteId,
                                     @Valid @RequestBody Cliente atualizacaoCliente )
    {
        return clienteService.atualizarCliente(clienteId, atualizacaoCliente);
    }

    @GetMapping("/pesquisaAvancada")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> filtroAvancadoCliente( RequestFiltroClienteDTO exemploCliente )
    {
        return clienteService.localizarClientePorObjetoDeFiltro(exemploCliente);
    }

    @GetMapping(params = {"contemTexto"})
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> filtroClientePorNome( @RequestParam("contemTexto") String contemTexto )
    {
        return clienteService.localizarClientesPorParteNomeOrdenado(contemTexto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> localizarTodosClientes( @RequestParam(required = false) Integer pagina,
                                                 @RequestParam(required = false) Integer quantidadeDeRegistros )
    {
        pagina = obterValorIntDeWrapper(pagina, 0);
        quantidadeDeRegistros = obterValorIntDeWrapper(quantidadeDeRegistros, 10);
        return clienteService
                .obterTodosClientes(pagina, quantidadeDeRegistros)
                .stream()
                .collect(Collectors.toList());
    }

    private int obterValorIntDeWrapper(Integer wrapper, int defaultValue) {
        if (wrapper == null)
            return defaultValue;
        else
            return wrapper;
    }
}
