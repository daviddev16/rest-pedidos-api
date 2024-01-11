package com.daviddev16.produto.impl;

import com.daviddev16.core.Defaults;
import com.daviddev16.produto.Produto;
import com.daviddev16.produto.ProdutoRepository;
import com.daviddev16.produto.ProdutoService;
import com.daviddev16.produto.exception.ProdutoNaoEncontradoException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private Produto internalLocalizarProdutoPorId(Integer produtoId) {
        return produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }

    @Override
    public Produto atualizarProduto(Integer produtoId, Produto atualizacaoProduto) {
        Produto produtoLocalizado = internalLocalizarProdutoPorId(produtoId);
        atualizacaoProduto.setId(produtoLocalizado.getId());
        return produtoRepository.save(atualizacaoProduto);
    }

    @Override
    public void removerProdutoPorId(Integer produtoId) {
        Produto produtoLocalizado = internalLocalizarProdutoPorId(produtoId);
        produtoRepository.delete(produtoLocalizado);
    }

    @Override
    public Produto incluirNovoProduto(Produto novoProduto) {
        return produtoRepository.save(novoProduto);
    }

    @Override
    public List<Produto> localizarProdutoPorObjetoDeFiltro(Produto exemploProduto) {

        final Example<Produto> exampleParaQuery = Defaults
                .criarExampleSimplesGenericoPara(exemploProduto);

        return produtoRepository.findAll(exampleParaQuery);

    }

    @Override
    public Produto localizarProdutoPorId(Integer produtoId) {
        return internalLocalizarProdutoPorId(produtoId);
    }

    @Override
    public List<Produto> obterTodosProdutos() {
        return produtoRepository.findAll();
    }
}
