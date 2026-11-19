# Gestione Ordini API

Servizio REST backend in Spring Boot per la gestione degli ordini.
La documentazione delle API è disponibile all'indirizzo `http://localhost:8080/swagger-ui.html` dove sono presenti tutti gli endpoint esposti dall'applicazione, con relative request e response. 
La documentazione è stata generata automaticamente tramite Swagger UI.

## Istruzioni Avvio Applicazione
Eseguire il comando `docker compose up -d` nel percorso del progetto, che avvierà il container Docker contenente l'applicazione e il database PostgreSQL. 
All'avvio, l'applicazione sarà accessibile all'indirizzo `http://localhost:8080`.

## Configurazione Database

L'applicazione utilizza il database PostgreSQL.
Configurazione presente in `src/main/resources/application.properties`:

```properties
# APPLICATION
spring.application.name=spring-course

# DATABASE
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5433/dblocale}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:locale}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:locale}
spring.datasource.driver-class-name=org.postgresql.Driver
# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
# SERVER
server.port=8080
