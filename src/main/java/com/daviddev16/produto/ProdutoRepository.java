package com.daviddev16.produto;

import com.daviddev16.core.BaseRepository;

public interface ProdutoRepository extends BaseRepository<Produto> {

    Produto findByDescricao(String descricao);

}
