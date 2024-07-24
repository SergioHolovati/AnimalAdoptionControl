# Projeto Animal Control

Este projeto utiliza Docker e Docker Compose para configurar e executar um ambiente com um banco de dados PostgreSQL e uma aplicação Spring Boot.
Este guia descreve como configurar e iniciar o projeto.

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Estrutura do Projeto

.
├── docker-compose.yml
├── init.sql
├── .env
└── ../SergioHolovati
└── Dockerfile

bash
Copiar código

## Passos para Executar

1. **Clone o Repositório**

   Clone o repositório do projeto:
   git clone https://github.com/SergioHolovati/AnimalAdoptionControl.git
   cd <NOME_DO_REPOSITORIO>

Configurar o Dockerfile

Certifique-se de que o Dockerfile no diretório ../SergioHolovati está configurado corretamente para construir sua aplicação Spring Boot.

Execute o seguinte comando na raiz do projeto para construir e iniciar os containers:

docker-compose up --build

Verificar os Logs

Verifique os logs para garantir que os containers foram iniciados corretamente:

docker-compose logs

Acessar a API

Após o início dos containers, você pode acessar a aplicação Spring Boot em http://localhost:8080. 
Certifique-se de substituir localhost e 8080 pelos valores apropriados se você estiver executando em um ambiente diferente.


Para parar e remover os containers, execute:
docker-compose down