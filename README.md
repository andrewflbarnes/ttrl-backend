# ttrl-backend

A backend server for returning results to the Ticket to Ride League (TTRL) page.

See the [swagger spec](api/src/main/resources/api.yml) for details.

### Build

```bash
mvn clean install
```

### Running

The backend can be run in a couple of ways
- (TODO) Run the war/jar generated in the `spring-boot-standalone` module
- Import the generated `ttrl-spring-boot-starter` lib into an existing Spring Boot application as a dependency

### Configuration

**ttrl.dao.type**: the type of DAO classes to instantiate  
`jdbc` - Enables JDBC in which case both an appropriate library and `sping.datasource.url` are expected  
`<not set>` (default)  - A dummy DAO with hardcoded data is exposed

**ttrl.dao.database**: the database type for JDBC - allows DB specific SQL loading from `src/main/resources/sql/<database>/`  
`postgres` (default) - Postgres specific SQL

NOTE: No DB libraries are provided by default in the `spring-boot-starter` module  

### Local testing

To test locally some SQL and a docker-compose file are provided for convenience. The [SQL](./test.sql) also reflects
the expected table/view structure. By default the DB is exposed on port `5555` with `postgres` as the DB, username and
password.

```bash
doocker-compose up -d

psql -h localhost -p 5555 postgres postgres < test.sql
```

Running the test `Application` class in `spring-boot-starter` will start an instance running which connects to this DB.
(TODO) Alternatively run the backend standalone or embedded with the below settings:
```yaml
spring:
  datasource:
    url: "jdbc:postgresql://localhost:5555/postgres?user=postgres&password=postgres"

ttrl:
  dao:
    type: jdbc
    database: postgres

```

User details can be retrieved using the below (port `8088` assuming the test `Application` class is used):
```bash
curl http://localhost:8088/users
```
