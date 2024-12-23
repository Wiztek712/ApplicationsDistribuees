# ApplicationsDistribuees
School Project

## Objectives 

Develop a Java web application powered by SpringBoot that enables :
- Listing of Teams (optional : and Players) in a classic web page
- Creation / update / delete of Teams (optional : and Players) through an API (advice : inspired by REST principles)
- Storing of Teams (optional : and Players) in a relational database (among PostgreSQL, or MySQL, or MariaDB) through JPA
- Reproductible build with Maven

## Deliverables

- Source code repository (including if needed Docker-compose, SQL files for the database)
- Any additional needed documentation (for starting and initializing the database for example)



docker run --name pg1 -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres1234! -e POSTGRES_DB=mydb -d postgres:15-alpine

docker pull postgres
docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
docker run --name postgreSQL -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres1234! -e POSTGRES_DB=mydb -d postgres