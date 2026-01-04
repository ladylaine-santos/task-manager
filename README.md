
📝 Task Manager 2026
![alt text](https://img.shields.io/github/actions/workflow/status/ladylaine-santos/task-manager/main.yml?branch=main&style=flat-square)
![alt text](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![alt text](https://img.shields.io/badge/Angular-20-red?style=flat-square)

🚀 O Desafio

Desenvolvimento de uma aplicação Fullstack moderna para gerenciamento de tarefas. O projeto aplica conceitos avançados de arquitetura, como DDD (Domain-Driven Design) e SOLID, além de integração resiliente com APIs externas via WebClient e gerenciamento de estado reativo com Angular Signals.

👤 Autor

Ladylaine Santos

Repositório: github.com/ladylaine-santos/task-manager

🛠 Tecnologias

* Backend: Java 21 LTS, Spring Boot 3.4, Spring Data JPA, WebClient.
* Frontend: Angular 20, Signals, Arquitetura Standalone, SCSS.
* Banco de Dados: Microsoft SQL Server 2022.
* Infraestrutura: Docker (DB), GitHub Actions (CI/CD), GitFlow.
  
🤖 GitHub Actions (CI/CD)

O projeto utiliza GitHub Actions para automatizar o ciclo de vida do desenvolvimento:

Integração Contínua (CI): A cada push ou pull request, um robô valida o código, instala dependências e executa o build.

Testes Automatizados: O pipeline sobe um container temporário de SQL Server para garantir que todos os testes de integração (JUnit/Mockito) passem antes do merge.

🔧 Instalação e Execução Local

1. Clonar o Repositório
````Bash
git clone https://github.com/ladylaine-santos/task-manager.git
cd task-manager
````

2. Iniciar o Banco de Dados (Docker)
   
Este comando sobe apenas o SQL Server, isolando a infraestrutura do banco:


````Bash
docker-compose up -d database
````

3. Executar o Backend (Java)
  
Certifique-se de estar na pasta do backend e execute:



````Bash
cd taskManager-backend
./mvnw spring-boot:run
````
API disponível em: http://localhost:8080/swagger-ui/index.html

4. Executar o Frontend (Angular)
   
Abra um novo terminal na pasta do frontend:

````Bash
cd task-manager-ui
npm install
npm start
````
Aplicação disponível em: http://localhost:4200
