# 🛒 Sistema de Gerenciamento de Produtos de Supermercado

Este projeto consiste em um sistema completo (Back-end + Front-end) para **cadastrar, consultar, atualizar e remover produtos** de um supermercado, respeitando regras de negócio específicas.

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
  Ex: `ALIMENTOS`, `BEBIDAS`, `PADARIA`, `HORTIFRUTI`

---

## 🧪 Tecnologias Utilizadas

### 🔙 Back-End
- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Docker

### 🔜 Front-End
- Angular 17+ (ou superior)
- TypeScript
- Bootstrap 5 / TailwindCSS

---

## 📦 Como rodar o projeto

### Pré-requisitos
- Java 17 ou 21
- Maven
- PostgreSQL
- Node + Angular CLI (para o front)

### Banco de Dados
```sql
CREATE DATABASE produto;

