package com.daviddev16.config.development;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.cliente.ClienteRepository;
import com.daviddev16.cliente.ClienteService;
import com.daviddev16.core.profiles.DevelopmentProfile;
import com.daviddev16.core.profiles.ProductionProfile;
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

    @Bean
    CommandLineRunner commandLineRunner() {
        return args ->
        {
            /* dev test aqui */
        };
    }

}
