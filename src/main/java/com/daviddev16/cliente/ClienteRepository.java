package com.daviddev16.cliente;


import com.daviddev16.core.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente> {

    /**
     * Realiza query de clientes utilizando o fetch para Pedidos e ItemPedidos,
     * visto que são definidos com fetchType de tipo LAZY.
     **/
    @Query(" SELECT c,p,ip FROM Cliente c LEFT JOIN FETCH c.pedidos p LEFT JOIN FETCH p.itens ip WHERE c.id = :paramId")
    Cliente findClienteFetchPedidosComItens( @Param("paramId") Integer id );

}
