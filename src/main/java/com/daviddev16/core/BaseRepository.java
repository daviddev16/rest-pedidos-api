package com.daviddev16.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/* Todas as entidades que possuem chave primária do tipo Integer */
public @NoRepositoryBean interface BaseRepository<T> extends JpaRepository<T, Integer> { }
