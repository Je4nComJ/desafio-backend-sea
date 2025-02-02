# 📌 Desafio Backend - API de Cadastro de Clientes

Este projeto é uma API RESTful desenvolvida em **Java com Spring Boot**, seguindo boas práticas de programação. A API permite o **cadastro, atualização, consulta e remoção de clientes**, além da integração com a API externa **ViaCEP** para buscar automaticamente o endereço do cliente.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (Persistência de dados)
- **Spring Security com JWT** (Autenticação)
- **Hibernate** (ORM)
- **Lombok** (Redução de boilerplate)
- **Postman** (Testes de API)
- - **H2-Console** (Banco de dados SQL)

---

## 📌 Endpoints da API

A documentação completa da API, incluindo exemplos de requisições e respostas, está disponível na Collection do Postman:
👉 **[Documentação Postman](https://documenter.getpostman.com/view/35096465/2sAYX3rijc)**

### 🔹 Autenticação
A API usa **JWT** para autenticação. Primeiro, gere um token:
```
POST /auth/login
```
Depois, utilize o token em todas as requisições autenticadas adicionando no header:
```
Authorization: Bearer <seu_token>
```

### 🔹 Cadastro de Cliente
```
POST /clientes
```
**Exemplo de JSON:**
```json
{
  "nome": "Maria Souza",
  "cpf": "98765432100",
  "cep": "01001000",
  "complemento": "Apto 202",
  "telefones": [
    {
      "tipoTelefone": "CELULAR",
      "numero": "11988887777"
    }
  ],
  "emails": [
    {
      "enderecoEmail": "maria@gmail.com"
    }
  ]
}
```

### 🔹 Atualizar Cliente
```
PUT /clientes/{id}
```

### 🔹 Listar Clientes
```
GET /clientes
```

### 🔹 Buscar Cliente por ID
```
GET /clientes/{id}
```

### 🔹 Excluir Cliente
```
DELETE /clientes/{id}
```

