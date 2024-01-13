package com.daviddev16.pedido.impl;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.ClienteService;
import com.daviddev16.pedido.*;
import com.daviddev16.pedido.dto.request.RequestPedidoDTO;
import com.daviddev16.pedido.exception.PedidoNaoEncontradoException;
import com.daviddev16.pedido.transformer.PedidoRequestTransformer;
import com.daviddev16.produto.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ClienteService clienteService;

    private final PedidoRepository pedidoRepository;
    private final  ItemPedidoRepository itemPedidoRepository;

    private final PedidoRequestTransformer requestTransformer;

    public PedidoServiceImpl(ClienteService clienteService, PedidoRequestTransformer requestTransformer,
                             ProdutoRepository produtoRepository, PedidoRepository pedidoRepository,
                             ItemPedidoRepository itemPedidoRepository)
    {
        this.clienteService = clienteService;
        this.requestTransformer = requestTransformer;

        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    private Pedido internalLocalizarPedidoPorId(Integer pedidoId) {
        return pedidoRepository
                .findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

    /***
     * Não utiliza internalLocalizarPedidoPorId por que os dados que serão
     * retornados não são iguais ao fetch do findbyId.
     * */
    @Override
    public Pedido obterPedidoCompleto(Integer pedidoId) {
        return pedidoRepository
                .findByIdFetchItens(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

    @Override
    @Transactional
    public void atualizaStatusPedido(Integer pedidoId, StatusPedido novoStatusPedido) {
        Pedido pedidoLocalizado = internalLocalizarPedidoPorId(pedidoId);
        pedidoLocalizado.setStatusPedido(novoStatusPedido);
        pedidoRepository.save(pedidoLocalizado);
    }

    @Override
    @Transactional
    public Pedido criarNovoPedido(RequestPedidoDTO requestPedidoDto) {

        final Integer clienteId = requestPedidoDto.getIdCliente();
        final Cliente clientePedido = clienteService.localizarClientePorId(clienteId);

        final Pedido novoPedidoCriado = Pedido
                .builder()
                    .cliente(clientePedido)
                    .statusPedido(StatusPedido.REALIZADO) /* STATUS INICIAL */
                    .valorTotal(requestPedidoDto.getValorTotalPedido())
                    .dataPedido(LocalDate.now())
                .build();

        /* convertendo itemPedidoDTO para itemPedido */
        Set<ItemPedido> itemPedidos = requestTransformer
                .transformarItemPedidoDTOemItemPedidos(novoPedidoCriado,
                        requestPedidoDto.getItensPedido());

        pedidoRepository.save(novoPedidoCriado);
        itemPedidoRepository.saveAll(itemPedidos);

        novoPedidoCriado.setItens(itemPedidos);

        return novoPedidoCriado;
    }

    public List<Pedido> obterTodosPedidos() {
        return pedidoRepository.findAll();
    }

}
