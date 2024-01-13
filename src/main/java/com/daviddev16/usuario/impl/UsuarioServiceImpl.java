package com.daviddev16.usuario.impl;

import com.daviddev16.usuario.Usuario;
import com.daviddev16.usuario.UsuarioRepository;
import com.daviddev16.usuario.UsuarioService;
import com.daviddev16.usuario.dto.request.RequestCredenciaisUsuarioDTO;
import com.daviddev16.usuario.exception.SenhaInvalidaException;
import com.daviddev16.usuario.exception.UsuarioNaoEncontradoException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(PasswordEncoder passwordEncoder,
                              UsuarioRepository usuarioRepository)
    {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuarioEncontrado = usuarioRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(username));

        return User
                .builder()
                    .username(usuarioEncontrado.getLogin())
                    .password(usuarioEncontrado.getSenha())
                    .roles(usuarioEncontrado.cargos())
                .build();

    }

    public UserDetails autenticarUsuario(RequestCredenciaisUsuarioDTO credenciaisUsuarioDTO) {

        UserDetails userDetails = loadUserByUsername(credenciaisUsuarioDTO.getLogin());

        if (!passwordEncoder.matches(credenciaisUsuarioDTO.getSenha(), userDetails.getPassword()))
            throw new SenhaInvalidaException();

        return userDetails;
    }

    private Usuario internalLocalizarUsuarioPorId(Integer usuarioId) {
        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Override
    @Transactional
    public Usuario incluirNovoUsuario(Usuario novoUsuario) {
        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        return usuarioRepository.save(novoUsuario);
    }

    @Override
    @Transactional
    public void removerUsuarioPorId(Integer usuarioId) {
        Usuario usuarioEncontrado = internalLocalizarUsuarioPorId(usuarioId);
        usuarioRepository.delete(usuarioEncontrado);
    }

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

}
