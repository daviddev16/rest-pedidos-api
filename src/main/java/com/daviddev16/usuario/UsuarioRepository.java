package com.daviddev16.usuario;

import com.daviddev16.core.BaseRepository;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario> {

    Optional<Usuario> findByLogin(String login);

}
