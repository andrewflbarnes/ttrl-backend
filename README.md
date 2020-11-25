# ttrl-backend

A backend server for returning results to the Ticket to Ride League (TTRL) page.

See the [swagger spec](api/src/main/resources/api.yml) for details.

### Build

```bash
mvn clean install
```

### Running

The backend can be run in a couple of ways
- Run the war generated in the `server` module e.g `java -jar .../ttrl-server.war`
- Import the generated `ttrl-spring-boot-starter` lib into an existing Spring Boot application as a dependency

### Configuration

**ttrl.dao.type**: the type of DAO classes to instantiate  
`jdbc` - Enables JDBC in which case both an appropriate library and `sping.datasource.url` are expected  
`<not set>` (default)  - A dummy DAO with hardcoded data is exposed

**ttrl.dao.database**: the database type for JDBC - allows DB specific SQL loading from `src/main/resources/sql/<database>/`  
`postgres` (default) - Postgres specific SQL

NOTE: No DB libraries are provided by default in the `spring-boot-starter` module  

### Local testing

To test locally some SQL and a docker-compose file are provided for convenience. The [SQL](./test/test.sql) also reflects
the expected table/view structure. By default the DB is exposed on port `5432` with `postgres` as the DB, username and
password.

```bash
cd test

doocker-compose up -d

psql -h localhost -p 5432 postgres postgres < test.sql
```

Run the `server` standalone.
```bash
cd server/target

java -jar ttrl-server*.war
```

User details can be retrieved using the below (port `8088` assuming the test `Application` class is used):
```bash
curl http://localhost:8088/users
```
