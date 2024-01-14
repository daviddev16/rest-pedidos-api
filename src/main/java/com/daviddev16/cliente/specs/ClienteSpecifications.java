package com.daviddev16.cliente.specs;

import com.daviddev16.cliente.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public final class ClienteSpecifications {

    private ClienteSpecifications() {}

    public static Specification<Cliente> nomeComecandoCom(String inicioNome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), inicioNome.concat("%"));
    }

    public static Specification<Cliente> propertyEquals(String nomeProp, Object valor) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(nomeProp), valor);
    }

    public static Specification<Cliente> conjuncao() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static void usos() {

        Integer id = 0;
        String nome = "David";
        String email = "davidduartepinheiro@gmail.com";

        Specification<Cliente> specs = conjuncao();
        specs = specs.and(propertyEquals("id", id));

        if (StringUtils.isEmpty(nome)) {
            specs = specs.and(propertyEquals("nome", nome));
        }
        if (StringUtils.isEmpty(email)) {
            specs = specs.and(propertyEquals("email", email));
        }
    }

}
