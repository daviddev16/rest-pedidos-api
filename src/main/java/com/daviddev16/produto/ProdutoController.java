package com.daviddev16.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value = "/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public Produto localizarProdutoPorId( @PathVariable("produtoId") Integer produtoId )
    {
        return produtoService.localizarProdutoPorId(produtoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto incluirNovoProduto( @Valid @RequestBody Produto novoProduto )
    {
        return produtoService.incluirNovoProduto(novoProduto);
    }

    @DeleteMapping(value = "/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProdutoPorId( @PathVariable Integer produtoId )
    {
        produtoService.removerProdutoPorId(produtoId);
    }

    @PutMapping(value = "/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto atualizarProduto( @PathVariable Integer produtoId,
                                     @Valid @RequestBody Produto atualizacaoProduto )
    {
        return produtoService.atualizarProduto(produtoId, atualizacaoProduto);
    }

    @GetMapping(value = "/pesquisaAvancada")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> filtroAvancadoProduto( Produto exemploProduto )
    {
        return produtoService.localizarProdutoPorObjetoDeFiltro(exemploProduto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> localizarTodosProdutos()
    {
        return produtoService.obterTodosProdutos();
    }

}
