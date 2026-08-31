# Sales API

Backend de uma plataforma de vendas de veículos desenvolvido em Java com Spring Boot. Fornece APIs RESTful para gerenciamento de veículos, autenticação de usuários, busca e filtragem de carros.

## Índice
- [Tecnologias](#tecnologias)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Como Executar](#como-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)

## Tecnologias
- **Java 17**: Linguagem principal do projeto
- **Spring Boot**: Framework para construção de aplicações Java e APIs RESTful
- **Spring Data JPA**: Persistência de dados e comunicação com banco de dados
- **Hibernate**: ORM para gerenciar comunicação com o banco de dados
- **PostgreSQL**: Banco de dados em produção
- **H2 Database**: Banco de dados em memória para testes
- **Maven**: Gerenciador de dependências
- **JUnit**: Framework para testes unitários

## Funcionalidades
- **Gerenciamento de Veículos**: Registrar, atualizar, remover e listar veículos disponíveis
- **Autenticação de Usuários**: Registro e login com criptografia de senhas
- **Busca de Veículos**: Filtrar por marca, modelo, ano e preço
- **Gerenciamento de Usuários**: Alteração de senha e gestão de informações

## Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior
- PostgreSQL 12 ou superior (opcional, pode usar H2 para desenvolvimento)

## Instalação
1. Clone o repositório:
   ```sh
   git clone https://github.com/JoseVictorMarques/sales.git
   cd sales
   ```

2. Instale as dependências:
   ```sh
   mvn clean install
   ```

## Como Executar
1. Configure as variáveis de ambiente no arquivo `.env`:
   ```sh
   cp .env.example .env
   ```

2. Execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

   A aplicação será iniciada em `http://localhost:8080`

3. Para executar os testes:
   ```sh
   mvn test
   ```

## Estrutura do Projeto
```
src/
├── main/
│   ├── java/com/example/sales/
│   │   ├── business/          # Regras de negócio
│   │   ├── config/            # Configurações (CORS, etc)
│   │   ├── controller/        # Controllers REST
│   │   ├── exception/         # Tratamento de exceções
│   │   ├── model/
│   │   │   ├── dtos/          # Data Transfer Objects
│   │   │   └── entities/      # Entidades JPA
│   │   └── repository/        # Interfaces de repositório
│   └── resources/
│       └── application.properties  # Configurações da aplicação
└── test/
    └── java/com/example/sales/    # Testes unitários
```
