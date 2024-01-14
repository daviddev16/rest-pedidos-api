package com.daviddev16.cliente;


import com.daviddev16.cliente.exception.ClienteNaoEncontradoException;
import com.daviddev16.core.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente> {

    /**
     * Realiza query de clientes utilizando o fetch para Pedidos e ItemPedidos,
     * visto que são definidos com fetchType de tipo LAZY.
     **/
    @Query(" SELECT c,p,ip FROM Cliente c LEFT JOIN FETCH c.pedidos p LEFT JOIN FETCH p.itens ip WHERE c.id = :paramId")
    Optional<Cliente> findClienteFetchPedidosComItens(@Param("paramId") Integer id )
            throws ClienteNaoEncontradoException;

    List<Cliente> findByNomeContaining(String nomeParte, Sort ordenacao);

    @Query(" SELECT c FROM Cliente c ")
    Page<Cliente> obterTodosClientesPaginado(Pageable pageable);

}
