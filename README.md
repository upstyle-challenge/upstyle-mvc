# UpStyle - Plataforma de Recomendações de Moda Personalizadas

### Grupo: UpStyle

**Integrantes:**
- Ian Navas - RM550133
- Miguel Santos -  RM551640
- João Gabriel Cardoso - RM552078
- Felipe Morais - RM551463

---

## Descrição do Projeto

**UpStyle** é uma plataforma de recomendações de moda personalizada desenvolvida como parte do projeto final da disciplina **Java Advanced**. Esta aplicação utiliza **Spring MVC** com **Thymeleaf** para o front-end, **Spring Security** para autenticação e gestão de perfis, **RabbitMQ** para mensageria e **Spring AI** para recomendações baseadas em inteligência artificial.

O projeto também inclui recursos de monitoramento com **Spring Boot Actuator** e internacionalização.

---

## Estrutura do Repositório

O repositório contém os seguintes diretórios e arquivos principais:
- `src/` - Código fonte do projeto, incluindo controllers, serviços, repositórios e modelos.
- `resources/templates/` - Templates Thymeleaf para as páginas da aplicação.
- `resources/static/` - Arquivos estáticos como CSS e imagens.
- `Dockerfile` - Dockerfile para facilitar a construção e execução da aplicação.
- `docker-compose.yml` - Definição de serviços Docker necessários para o projeto.
- `README.md` - Este documento.

---

## Configuração e Execução do Projeto

### Pré-requisitos

Certifique-se de ter os seguintes softwares instalados:
- **Docker** e **Docker Compose**
- **JDK 17** ou superior
- **Maven**

### Passos para Configurar o Ambiente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/upstyle.git
   cd upstyle

2. Clone o repositório:
   ```bash
   mvn clean install

3. Inicialize os containers necessários no Docker:
  - Inicie o container RabbitMQ com a interface de gerenciamento:
    ```bash
    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
  - Inicie o container ollama para o modelo de IA Llama:
      ```bash
    docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
    docker exec -it ollama ollama run llama3.2

4. Inicie a aplicação Spring Boot:
   ```bash
   mvn spring-boot:run

6. Acesse a aplicação no navegador:
- URL da aplicação: http://localhost:8080
- URL do RabbitMQ: http://localhost:15672 (usuário: guest, senha: guest)

## Funcionalidades Principais
- **Autenticação e Autorização:** Implementada com Spring Security, com gestão de perfis de usuário.
- **Recomendações Personalizadas:** Geração de recomendações de moda usando Spring AI e o modelo Llama, com base nos dados do perfil do usuário.
- **Mensageria com RabbitMQ:** Configuração de troca de mensagens para processamentos assíncronos.
- **Monitoramento:** Implementado com Spring Boot Actuator para monitorar a saúde da aplicação e estatísticas.
- **Internacionalização:** Suporte para múltiplos idiomas, permitindo a personalização da experiência do usuário.

---

## Instruções de Teste e Acesso
1. **Registro e Login:**
- Acesse a página de registro para criar um novo usuário.
- Em seguida, faça o login usando as credenciais criadas.

2. **Página Inicial:**
- Após o login, você será redirecionado para a página inicial, onde verá uma saudação personalizada e as recomendações de moda.

3. **Acesso ao RabbitMQ:**
- Acesse a interface de gerenciamento do RabbitMQ em http://localhost:15672 para monitorar as filas de mensagens.

4. **Monitoramento da Aplicação:**
- Verifique o estado da aplicação e outras métricas acessando o Spring Boot Actuator em http://localhost:8080/actuator.


---

## Tecnologias Utilizadas
- Java 17
- Spring Boot, Spring MVC, Spring Security
- Thymeleaf
- RabbitMQ
- Docker
- Spring Boot Actuator
- Spring AI e Llama para recomendações personalizadas

---

## Vídeo Demonstrativo
Assista ao vídeo de apresentação e demonstração da aplicação [https://linkdovideo](aqui).
