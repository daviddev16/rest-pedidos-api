package com.daviddev16.usuario;


import com.daviddev16.security.JwtService;
import com.daviddev16.usuario.dto.request.RequestCredenciaisUsuarioDTO;
import com.daviddev16.usuario.dto.response.ResponseTokenUsuarioDTO;
import com.daviddev16.usuario.dto.response.ResponseUsuarioDTO;
import com.daviddev16.usuario.exception.SenhaInvalidaException;
import com.daviddev16.usuario.transformer.UsuarioResponseTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioResponseTransformer usuarioResponseTransformer;
    private final JwtService jwtService;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioResponseTransformer usuarioResponseTransformer,
                             JwtService jwtService)
    {
        this.usuarioService = usuarioService;
        this.usuarioResponseTransformer = usuarioResponseTransformer;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUsuarioDTO incluirNovoUsuario( @Valid @RequestBody Usuario novoUsuario )
    {
        return usuarioResponseTransformer
                .transformarUsuarioEmResponseDTO( usuarioService.incluirNovoUsuario(novoUsuario) );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseUsuarioDTO> obterTodosUsuarios()
    {
        return usuarioResponseTransformer
                .transformarListaUsuarioEmResponseDTOs( usuarioService.obterTodosUsuarios() );
    }

    @PostMapping(value = "/auth")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTokenUsuarioDTO autenticarUsuario( @RequestBody RequestCredenciaisUsuarioDTO credenciaisUsuarioDTO )
    {
        try {
            final UserDetails userDetails = usuarioService.autenticarUsuario(credenciaisUsuarioDTO);
            final String novoTokenJwtParaUsuario = jwtService.gerarTokenJwt(credenciaisUsuarioDTO.getLogin());

            return usuarioResponseTransformer
                    .criarResponseParaTokenJwt(novoTokenJwtParaUsuario, userDetails);

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
