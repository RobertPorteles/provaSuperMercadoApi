
# 🛒 Sistema de Gerenciamento de Produtos de Supermercado

Este projeto é um sistema completo (Back-end + Front-end) para **cadastrar, consultar, atualizar e remover produtos** de um supermercado, com regras de negócio aplicadas.

💡 **Totalmente virtualizado com Docker**: você não precisa instalar Java, PostgreSQL ou Maven para rodar — tudo sobe com um simples comando via Docker Compose!

---

## ✅ Funcionalidades

- ✅ Cadastrar produtos com nome, preço, estoque e categoria  
- ✅ Listar todos os produtos  
- ✅ Atualizar dados de um produto existente  
- ✅ Excluir produtos  
- ✅ Validações de negócio aplicadas  

---

## ⚖️ Regras de Negócio

- ❌ Não é permitido cadastrar produtos com **nome duplicado**  
- ❌ Não é permitido **excluir produtos com estoque maior que 0**  
- ❌ O **preço** de um produto não pode ser **negativo**  
- ⚠️ A **categoria é obrigatória** no cadastro/edição  
  Exemplo: `ALIMENTOS`, `BEBIDAS`, `PADARIA`, `HORTIFRUTI`

---

## 🧪 Testes Automatizados com JUnit e MockMvc

A classe `ProvaTesteSuperMercadoApiApplicationTests` valida o comportamento da API usando **JUnit 5 + MockMvc**, garantindo:

- ✅ Criação de produto com sucesso  
- 🚫 Bloqueio de criação com nome duplicado  
- 🚫 Bloqueio de exclusão se o produto tem estoque  
- ✅ Permissão de exclusão se o estoque for zero  

---

## 🛠️ Tecnologias Utilizadas

### 🔙 Back-End
- Java 21  
- Spring Boot 3.5.x  
- Spring Data JPA  
- PostgreSQL  
- Maven Wrapper  
- Lombok  
- Docker
- JUnit e MockMvc  

### 🔜 Front-End
- Angular 17+  
- TypeScript  
- Bootstrap 5 / TailwindCSS  

---

## 🚀 Como rodar o projeto (100% via Docker)

> ✅ Basta ter o [Docker](https://www.docker.com/products/docker-desktop) instalado.

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/seu-usuario/provaSuperMercadoApi.git
cd provaSuperMercadoApi
