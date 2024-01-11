package com.daviddev16.produto;


import java.util.List;

public interface ProdutoService {

    Produto localizarProdutoPorId(Integer produtoId);

    Produto atualizarProduto(Integer produtoId, Produto atualizacaoProduto );

    void removerProdutoPorId( Integer produtoId );

    Produto incluirNovoProduto( Produto novoProduto );

    List<Produto> localizarProdutoPorObjetoDeFiltro(Produto exemploProduto );

    List<Produto> obterTodosProdutos();

}
