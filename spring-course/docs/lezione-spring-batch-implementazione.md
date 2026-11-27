# Spring Batch - implementazione nel progetto spring-course

## Scenario
Importazione di `src/main/resources/batch/products.csv` nella tabella PostgreSQL `products`. Il flusso è Reader -> Processor -> Writer, chunk size 3. Il reader è `@StepScope`, così ogni esecuzione dello Step riceve un reader separato (il reader mantiene internamente lo stato di lettura necessario al restart).

## Versioni e dipendenze
- Java 25
- Spring Boot 4.1.1
- Spring Batch 6.0.5 (versione gestita dal BOM Spring Boot)
- `spring-boot-starter-batch-jdbc`

## Migrazioni
`V5__create_spring_batch_metadata.sql` crea le tabelle e le sequence metadata di Spring Batch 6.0.5 per PostgreSQL. Nel profilo `test` la creazione automatica dello schema Batch è disabilitata perché è gestita da Flyway.

## Esecuzione
1. Avvia PostgreSQL (`docker compose up -d postgres-db`).
2. Avvia l'applicazione: il profilo predefinito è `test` (oppure imposta esplicitamente `SPRING_PROFILES_ACTIVE=test`) e `JWT_SECRET` deve essere valorizzata.
3. Ottieni un token con gli endpoint auth già presenti.
4. `POST /api/batch/products/import` avvia il Job e risponde `202 Accepted`.
5. Usa `GET /api/batch/jobs/{executionId}` per leggere stato e contatori dello Step.

Gli endpoint sono protetti dalle regole Spring Security esistenti: serve un JWT valido.

## CSV di esempio
Il file contiene sei righe con prezzo numerico e una riga con prezzo non numerico. Il Processor filtra il prezzo negativo; il Reader salta la riga non convertibile (massimo 5 errori di parsing). Risultato atteso: 5 prodotti scritti, 1 elemento filtrato e 1 read skip.

## Attenzione alle reimportazioni
Ogni POST genera un parametro `run.id` (UUID) univoco e quindi una nuova JobInstance. Il file è statico e il writer esegue INSERT: ripetere l'importazione inserisce di nuovo i prodotti validi. Questa demo non implementa ancora un'identità business / upsert per rendere l'import idempotente.

## Test automatici
Anche gli integration test Testcontainers usano Flyway: `src/test/resources/application-test.properties` è allineato al profilo `test`, quindi la migrazione V5 viene applicata al PostgreSQL effimero del test. Spring Batch non inizializza autonomamente lo schema (`initialize-schema=never`).