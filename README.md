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


# Instructions

## Build the project

You can build the project using Maven.

## Deploy the database

This project uses PostgreSQL container for storing application data.
Assuming your docker installation is functionnal, you can use these commands to set up your environment :  
```bash
docker pull postgres
docker run --name postgreSQL -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres1234! -e POSTGRES_DB=mydb -d postgres
```
Then, let's go to src.main.java.com.example.project.ressources and modify _application.properties_ file. 
Please, set **spring.datasource.url** according to your configuration.

## Run the code

Now, you are able to run src/main/java/com/example/project/_ProjectApplication.java_.

## Code explanations

Here is a brief review of the code : 
- pilote and team folders store entity, repository and controller java files.
- LoadDatabase.java loads demo data.
- Please find API explanations directly in the controller functions.
- Go to http://localhost:8080/ to see the pilote and team lists.
- You can run tests for some basic features.

## API Requests examples

Here is 4 basic queries you can perform with Postman. Don't hesitate to also have a look to id-based requests.

GET http://localhost:8080/pilotes
GET http://localhost:8080/teams
POST http://localhost:8080/pilotes?name=Isack&number=34
POST http://localhost:8080/teams?name=Aston Martin&headQuarters=Silverstone
