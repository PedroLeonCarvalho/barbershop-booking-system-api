# Spring Security Template


Este projeto tem como objetivo fornecer um modelo (template) reutilizável de uma aplicação Spring Security com diversas configurações pré-configuradas. Ele pode ser facilmente adaptado para qualquer aplicação onde seja necessária autenticação de usuários com base em **Roles** (perfis de usuário), **JWT**, **BCrypt**, e integração com um banco de dados MySQL em contêiner Docker. O projeto também inclui exemplos de endpoints para login, cadastro de novos usuários e um usuário administrador já cadastrado.


Video explicativo: https://www.youtube.com/watch?v=Z61r-QFBSvE

## Funcionalidades

- Autenticação de usuários usando **JWT (JSON Web Token)**.
- Criptografia de senhas com **BCrypt**.
- **Roles** configuráveis para controle de acesso (**ROLE_USER**, **ROLE_ADMIN**).
- Integração com **MySQL** em contêiner Docker.
- Endpoint para **login** de usuários.
- Endpoint para **cadastro de novos usuários**.
- **Usuário administrador** já cadastrado para facilitar a inicialização e testes.
  
## Tecnologias Utilizadas

- **Spring Boot** 3.x
- **Spring Security**
- **JWT** para autenticação e autorização
- **BCrypt** para criptografia de senha
- **MySQL** como banco de dados relacional
- **Docker** para gerenciamento de contêineres
- **Hibernate** para ORM (Mapeamento Objeto Relacional)

## Estrutura de Roles e Usuários

### Roles

Este projeto utiliza o conceito de **Roles** para definir permissões de acesso. As roles estão armazenadas no banco de dados e são associadas a cada usuário:

- `ROLE_USER`: Permissão para acesso básico.
- `ROLE_ADMIN`: Permissão para acesso administrativo.

### Usuário Administrador

Um usuário administrador já está cadastrado no banco de dados para facilitar o uso imediato do projeto. As credenciais do usuário são:

- **Email**: `usuario@email.com`
- **Password**: `senha`
- **Role**: `ROLE_ADMIN`

## Endpoints

### Login

Endpoint para autenticação de usuários. Retorna um token JWT caso as credenciais estejam corretas.


## Como Rodar o Projeto Localmente

### Pré-requisitos

Para rodar o projeto localmente, você precisará ter os seguintes componentes instalados:

- **Java 17** ou superior
- **Maven**
- **Docker**

### Passo a Passo

1. **Clone o Repositório**

   Clone o projeto para o seu ambiente local utilizando o seguinte comando:

   ```bash
   git clone git@github.com:PedroLeonCarvalho/Spring-Security-Authentication-Template.git
   cd Spring-Security-Authentication-Template

   ### Baixe e Inicie um Container MySQL

### Configuração do Projeto com MySQL

O projeto está configurado para conectar-se ao MySQL container na porta padrão (`3306`), com o usuário `root`, senha `mysql`, e o banco de dados `barbearia_db`.

Caso você precise alterar essas configurações, edite o arquivo `application.properties` conforme necessário:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/barbearia_db
spring.datasource.username=root
spring.datasource.password=mysql


### Endpoints da API

#### 1. Login

- **POST** : `http://localhost:8080/login`

  Para realizar o login, envie uma requisição com as credenciais de email e senha no seguinte formato:

  ```json
  {
    "email": "usuario@email.com",
    "password": "senha"
  }

- **POST** : `http://localhost:8080/users`
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "phoneNumber": "1234567890",
  "password": "senha",
  "birthDate": "1990-01-01"
}

       
       
