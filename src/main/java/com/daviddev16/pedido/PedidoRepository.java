package com.daviddev16.pedido;

import com.daviddev16.cliente.Cliente;
import com.daviddev16.core.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends BaseRepository<Pedido> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :pedidoId")
    Optional<Pedido> findByIdFetchItens(@Param("pedidoId") Integer pedidoId);

}
