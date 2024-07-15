# ProductOrderManager API

## Descrição
ProductOrderManager é uma API RESTful desenvolvida para gerenciar clientes, produtos, empresas e pedidos. Utiliza Swagger para documentar todos os endpoints e Docker para facilitar a implantação em ambientes containerizados.

## Funcionalidades

- CRUD completo para Cliente, Produto, Empresa
- Operações GET, POST, PUT para Pedido
- Documentação detalhada com Swagger
- Testes automatizados para operações CRUD de Produto
- Validação de parâmetros de entrada
- Implementação de HATEOAS para navegação entre recursos
- Estrutura de serviços baseada em UseCase

## Diagrama Entidade Relacionamento

![Diagrama](https://i.imgur.com/VNeEN3c.png)

## Requisitos de Instalação

Para executar a API localmente, certifique-se de ter instalado:

- Docker
- Docker Compose
- Ou, simplemente, clone o repositório e execute a API (caso tenha todo o ambiente necessário)

### Iniciando a API

1. Clone o repositório e execute o projeto:

   ```bash
   git clone https://github.com/eduardoAssuncao/ProductOrderManager.git
   cd ProductOrderManager
   ```

2. Ou inicie a API com o Docker:

    ```bash
    docker-compose up -d --build
    ```


## Documentação
A documentação detalhada da API está disponível via Swagger em: `http://localhost:8080/swagger-ui/index.html`

##Fluxo do Swagger para testar API:
Criar Empresa -> Criar Cliente -> Criar Produto -> Criar Pedido -> Adionar Item ao Pedido -> Realizar Pagamento do Pedido

## Contribuindo
Fique à vontade para contribuir com novas funcionalidades, correções de bugs ou sugestões de melhorias.
