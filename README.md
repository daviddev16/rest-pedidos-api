
# RESTful API de Pedidos

REST API desenvolvida em Java, com Spring Boot (WEB + JPA), PostgreSQL e Lombok.
[projeto de rest-pedidos-api](https://github.com/users/daviddev16/projects/5)

## Pedidos
````
# Criar pedido novo
POST http://localhost:8080/api/pedidos

# Listar todos os pedidos
GET http://localhost:8080/api/pedidos

# Obter pedido por Id
GET http://localhost:8080/api/pedidos/{clienteId}

# Atualização de status do pedido (REALIZADO, ENTREGUE e CANCELADO)
PATCH http://localhost:8080/api/pedidos/10
````

## Produtos
````
# Criar produto novo
POST http://localhost:8080/api/produtos

# Listar todos os produtos
GET http://localhost:8080/api/produtos

# Obter produto por Id
GET http://localhost:8080/api/produtos/{produtoId}

# Pesquisa avançada de produto
GET http://localhost:8080/api/produtos/pesquisaAvancada?descricao=produto

# Deleta produto por Id
DELETE http://localhost:8080/api/produtos/{produtoId}

# Atualiza produto por Id
PUT http://localhost:8080/api/produtos/{produtoId}
````

## Clientes
````
# Criar cliente novo
POST http://localhost:8080/api/clientes

# Listar todos os clientes
GET http://localhost:8080/api/clientes

# Obter cliente por Id
GET http://localhost:8080/api/clientes/{clienteId}

# Pesquisa avançada de cliente
GET http://localhost:8080/api/clientes/pesquisaAvancada?descricao=produto

# Deleta cliente por Id
DELETE http://localhost:8080/api/clientes/{clienteId}

# Atualiza cliente por Id
PUT http://localhost:8080/api/clientes/{clienteId}
````
