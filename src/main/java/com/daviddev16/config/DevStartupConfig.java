package com.daviddev16.config;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.ClienteRepository;
import com.daviddev16.core.DevelopmentProfile;
import com.daviddev16.pedido.ItemPedido;
import com.daviddev16.pedido.Pedido;
import com.daviddev16.pedido.PedidoRepository;
import com.daviddev16.produto.Produto;
import com.daviddev16.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@DevelopmentProfile
public class DevStartupConfig {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args ->
        {
            clienteRepository.save(new Cliente("David"));
            clienteRepository.save(new Cliente("Fulano"));
            clienteRepository.save(new Cliente("Lucas"));
            clienteRepository.save(new Cliente("Maria"));
            clienteRepository.save(new Cliente("Ana"));

            Produto novoProduto = new Produto();
            novoProduto.setValor(BigDecimal.valueOf(20d));
            novoProduto.setDescricao("Produto 1");
            produtoRepository.save(novoProduto);

        };
    }

}
