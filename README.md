## Pre requirements
 - Java
 - Docker
 - Maven
## To run database in local environment - test purposes
`docker run -d -p 5432:5432 --env POSTGRES_USER=postgres --env POSTGRES_PASSWORD=password --env POSTGRES_DB=ladowarki library/postgres`

This command locally run PostgreSQL on port 5432.

## To build and deploy car-chargers app
`mvn package`<br>
`docker build -t car-chargers:[tag] .`<br>
`docker login`<br>
`docker tag car-chargers:[tag] bszabat/car-chargers:[tag]`<br>
`docker push bszabat/car-chargers:[tag]`

## To run application
`docker-compose up`