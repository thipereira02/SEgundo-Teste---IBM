# Segundo-Teste_IBM

Este é um projeto de exemplo que demonstra um sistema de controle de candidatos para um processo de seleção. O objetivo do projeto é gerenciar o fluxo de candidatos, desde o registro inicial até a aprovação ou desqualificação.

## Tecnologias
As seguintes ferramentas e frameworks foram utilizados na construção desse projeto:<br>

  ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)&nbsp;
  ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)&nbsp;
  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)&nbsp;
  
## Funcionalidades

O sistema possui os seguintes endpoints implementados:

- POST /api/v1/hiring/start: Inicia o processo de contratação de um candidato. Recebe um nome como parâmetro no corpo da requisição e retorna o ID do candidato criado.
- POST /api/v1/hiring/schedule: Marca uma entrevista para um candidato. Recebe o código do candidato como parâmetro no corpo da requisição.
- POST /api/v1/hiring/disqualify: Desqualifica um candidato. Recebe o código do candidato como parâmetro no corpo da requisição.
- POST /api/v1/hiring/approve: Aprova um candidato qualificado. Recebe o código do candidato como parâmetro no corpo da requisição.
- GET /api/v1/hiring/status/candidate/{codCandidato}: Retorna o status de um candidato com base no seu código.
- GET /api/v1/hiring/approved: Obtém a lista de candidatos aprovados

## Como Executar o Projeto

Para executar o projeto, siga as etapas abaixo:

1. Certifique-se de ter o Java 17 (ou superior) e o Apache Maven instalados em sua máquina.
2. Clone o repositório para sua máquina local.
 ``
git clone https://github.com/thipereira02/Segundo-Teste_IBM
``
3. Navegue até o diretório do projeto clonado.
3. Abra o projeto em sua IDE de preferência (recomendamos o uso do IntelliJ IDEA ou Eclipse).
4. Aguarde a resolução das dependências do projeto.
5. Compile o projeto usando o Maven.
    ``
    mvn clean install
    ``
6. Execute o projeto.
    ``
    mvn spring-boot:run
    ``
6. O aplicativo será iniciado e estará disponível em http://localhost:8080.
