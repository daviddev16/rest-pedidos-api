package com.daviddev16.core.util;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * Algumas funções genéricas e estáticas.
 * */
public final class QueryDefaults {

    /**
     * Definição do ExampleMatcher que será usado, assim, não precisará
     * criar instâncias do mesmo ExampleMatcher toda requisição.
     * */
    private static final ExampleMatcher DEFAULT_MATCHER;

    static
    {
        DEFAULT_MATCHER = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    /**
     * Cria um objeto Example<T> genérico para utilização na pesquisa de queries
     * através de uma entidade modelo. Será util para diversos Services que utilizaram
     * o mesmo tipo de pesquisa.
     * */
    public static <T> Example<T> criarExampleSimplesGenericoPara(T entidade) {
        return Example.of(entidade, DEFAULT_MATCHER);
    }

}
