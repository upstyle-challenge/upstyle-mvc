## Execute commands
- ```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management```
- ```docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama```
- ```docker exec -it ollama ollama run llama3.2```

# Código Fonte do Projeto

O projeto contempla os seguintes tópicos:

- [x] Funcionalidades de autenticação através da implementação do Spring Security (com gestão de perfis de segurança);
- [x] Implementação de recursos de internacionalização;
- [x] Configuração de recursos de mensageria (produtores e consumidores);
- [x] Implementação de monitoramento com o Spring Boot Actuator;
- [x] Implementação de recursos de inteligência artificial utilizando o framework Spring AI;
