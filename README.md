## Pre requirements
 - Java
 - Docker
 - Maven
## To run database in local environment
`docker run -d -p 5000:5432 --env POSTGRES_USER=postgres --env POSTGRES_PASSWORD=postgres --env POSTGRES_DB=postgres library/postgres`

This command locally run PostgreSQL on port 5000.