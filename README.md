# Docker-compose

Dette prosjektet er laget for å gi en kort innføring i hvordan man kan benytte verktøyet `docker-compose`.  Vi vil se på
hvordan `docker-compose` kan brukes for å kjøre databaser og andre tjenester i Docker, og koble mot disse fra applikasjoner
vi kjører lokalt.

Vi vil også se på hvordan vi også kan kjøre applikasjonene våre i Docker, koblet mot databaser og andre
tjenester som også kjører der.

Alt som beskrives her også kan gjøres via `docker run`.  `docker-compose` gjør det mye enklere å definere og kjøre applikasjoner
som består av flere containere.

## Prerequisites

Du trenger følgende programvare installert:

* IntelliJ IDEA **Professional Edition**
* Docker CE

Lag en runtime-konfigurasjon i IDEA for `docker-compose`, og pek mot `servers.yml`, og start den.

Lag en databasekobling i IDEA med følgende URL: `jdbc:postgresql://localhost:5432/testdb`.  Brukernavn/passord er
`postgres/postgres`.

## Starte Spring Boot-applikasjoner

Dette prosjektet innheholder også tre Spring Boot-applikasjoner.  Her følger en kort beskrivelse av hver applikasjon:

### Producer

Skriver ut meldinger på ActiveMQ-topicet `sample-topic`.

### Consumer

Konsumerer meldinger som skrives ut på ActiveMQ-topicet `sample-topic`, av Producer.

### Persister

Konsumerer og persisterer meldinger som skrives ut på ActiveMQ-topicet `sample-topic`, av Producer.

Alle prosjektene bruker **ActiveMQ**-instansen vi startet med `docker-compose`, mens `Persister`-prosjektet bruker *PostGreSQL**-instansen
i tillegg.

Kjør opp alle prosjektene i IntelliJ IDEA (Det skal være generert runtime-konfigurasjoner for dem automatisk), og studér
loggene fra hver av applikasjonene.  Kontrollér også at det kommer data inn i databasen.


## Kjøre Spring Boot-applikasjonene i Docker

Slett containerne fra den forrige kjøringen, og lag en ny runtime-konfigurasjon for `docker-compose`, denne gang mot
`servers_and_services.yml`.

Åpne opp en terminal / konsoll på rot av prosjektet, og kjør følgende:

`./gradlew build`

...eller hvis bruker Windows:

`gradlew build`

Dette vil bygge alle tre Spring Boot-applikasjonene, og publisere docker-images av dem i det lokale repositoriet.

Kjør runtime-konfigurasjonen.