# Docker-compose

Dette prosjektet er laget for å gi en kort innføring i hvordan man kan benytte verktøyet `docker-compose`.  Vi vil se på
hvordan `docker-compose` kan brukes kun for å kjøre f.eks. databaser i docker.  Vi vil også se hvordan vi kan kjøre både
ActiveMQ, MSSQL og flere Spring Boot applikasjoner i docker.

## Prerequisites

Du trenger følgende programvare installert:

* IntelliJ IDEA
* Docker CE

Lag en runtime-config for `docker-compose`, og pek mot `servers.yml`.  Klikk run.

Lag en databasekobling i IDEA med følgende URL: `jdbc:sqlserver://localhost:1433`.  Brukernavn/passord er `SA/S3cr3tpassw0rd`.

## Spring Boot applikasjoner

Dette prosjektet innheholder tre Spring Boot applikasjoner, som bruker **ActiveMQ** og **MSSQL**, startet via `docker-compose`.
Kjør opp hver av disse i IDEA, og studer loggene fra hver av applikasjonene.

### Producer

Skriver ut meldinger på ActiveMQ-topicet `sample-topic`.

### Consumer

Konsumerer meldinger som skrives ut på ActiveMQ-topicet `sample-topic`, av Producer.

### Persister

Konsumerer og persisterer meldinger som skrives ut på ActiveMQ-topicet `sample-topic`, av Producer.
