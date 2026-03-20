#  Biblioteca API

##  Sobre o projeto
A **Biblioteca API** é uma aplicação REST construída em **Java 17** com **Spring Boot**, que simula o fluxo de um sistema de gerenciamento de livros:

Cliente → API REST → Banco em memória (H2) → Resposta

Funcionalidades implementadas:

- Listar livros
- Buscar livro por ID
- Cadastrar livro
- Atualizar livro
- Remover livro
- Simular empréstimo de livro
- Simular devolução de livro
- Consultar banco via console H2

---

##  Objetivos de aprendizagem
Ao concluir este projeto, você será capaz de:

- Criar e executar uma API REST com **Spring Boot**
- Aplicar arquitetura em camadas (controller, service, repository, model)
- Integrar persistência com **Spring Data JPA**
- Utilizar banco em memória **H2** para testes rápidos
- Validar endpoints HTTP com ferramentas de API (Postman ou Insomnia)

---

##  Tecnologias utilizadas
- **Java 17**
- **Spring Boot 3.5.11**
- Spring Web
- Spring Data JPA
- H2 Database (em memória)
- Lombok
- Spring Boot DevTools
- Maven Wrapper (`mvnw` / `mvnw.cmd`)

---

##  Estrutura do projeto

```
biblioteca-api/
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
├── docs/
│   └── screenshots/
├── src/
│   ├── main/
│   │   ├── java/com/facens/biblioteca_api/
│   │   │   ├── BibliotecaApiApplication.java
│   │   │   ├── controller/LivroController.java
│   │   │   ├── service/LivroService.java
│   │   │   ├── repository/LivroRepository.java
│   │   │   └── model/Livro.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/java/com/facens/biblioteca_api/
│       └── BibliotecaApiApplicationTests.java
└── target/
```
## Pré-requisitos

Antes de executar o projeto, você precisa ter instalado:

VS Code

- Java JDK 17
- Extensões do VS Code:
- Extension Pack for Java
- Spring Boot Extension Pack
- Git

Configurações:

Language: Java
Spring Boot: 3.5.11
Group Id: com.facens
Artifact Id: biblioteca-api
Packaging: Jar
Java: 17

Dependências:

1. Spring Web
2. Spring Boot DevTools
3. Lombok
4. Spring Data JPA
5. H2 Database

2️⃣ Estrutura em camadas

Criar pacotes dentro de:
[-src/main/java/com/facens/biblioteca_api/
-controller
-service
-repository
-model

3️⃣ Configuração do H2 (application.properties)

spring.datasource.url=jdbc:h2:mem:biblioteca
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4️⃣ Modelo da entidade Livro

Campos utilizados:

id

titulo

autor

emprestado

dataEmprestimo

5️⃣ Repositório LivroRepository

Interface estendendo JpaRepository<Livro, Long>.

6️⃣ Serviço LivroService

CRUD completo

Empréstimo de livro (não permite emprestar livro já emprestado)

Devolução de livro (não permite devolver livro não emprestado)

7️⃣ Controller LivroController

Mapeamento base: /livros

## Endpoints disponíveis:

(Método - Endpoint
- Descrição)
  
GET	/livros:	
Lista todos os livros

GET	/livros/teste:
Verifica se API está ativa

GET	/livros/{id}:	
Busca livro por ID

POST	/livros:
Cadastra novo livro

PUT	/livros/{id}:
Atualiza dados do livro

DELETE	/livros/{id}:
Remove livro

PUT	/livros/{id}/emprestar:
Marca livro como emprestado

PUT	/livros/{id}/devolver:
Marca livro como devolvido

Exemplo de body para criação/atualização:
{
  "titulo": "Java para Web",
  "autor": "William Alves"
}

## Dados iniciais no H2

O projeto já carrega 3 livros automaticamente via data.sql.

Exemplo de consulta:

GET http://localhost:8080/livros
## Testando no Postman / Insomnia

Sequência recomendada:

1. GET /livros/teste → valida se API está ativa
2. GET /livros → lista livros existentes
3. POST /livros → cria novo livro
4. GET /livros/{id} → consulta livro criado
5. PUT /livros/{id} → atualiza livro
6. PUT /livros/{id}/emprestar → empresta livro
7. PUT /livros/{id}/devolver → devolve livro
8. DELETE /livros/{id} → remove livro

## Solução de problemas comuns

!Porta 8080 ocupada: alterar server.port=8081 no application.properties
!Wrapper não executa no Windows: usar .\mvnw.cmd clean test

H2 não abre: verificar URL JDBC jdbc:h2:mem:biblioteca e usuário sa sem senha
