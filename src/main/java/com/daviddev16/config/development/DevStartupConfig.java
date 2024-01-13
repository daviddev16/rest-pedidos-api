package com.daviddev16.config.development;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.ClienteRepository;
import com.daviddev16.core.profiles.DevelopmentProfile;
import com.daviddev16.pedido.PedidoRepository;
import com.daviddev16.produto.Produto;
import com.daviddev16.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

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
            /*clienteRepository.save(Cliente.builder().nome("David").cpf("40240627008").build());
            clienteRepository.save(Cliente.builder().nome("Fulano").build());
            clienteRepository.save(Cliente.builder().nome("Lucas").build());
            clienteRepository.save(Cliente.builder().nome("Maria").build());
            clienteRepository.save(Cliente.builder().nome("Ana").build());

            Produto novoProduto = new Produto();
            novoProduto.setValor(BigDecimal.valueOf(20d));
            novoProduto.setDescricao("Produto 1");
            produtoRepository.save(novoProduto);*/

        };
    }

}
