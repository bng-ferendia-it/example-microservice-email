# email-service
Email Service 

application port is 8082

For swagger api use http://localhost:8082/swagger-ui/
## Application arguments
````
APP_DNS:http://example.com

EMAIL_HOST:smtp.zoho.com
EMAIL_PORT:587
EMAIL_USER
EMAIL_PASSWORD

DB_URL:jdbc:postgresql://localhost:5432/test
B_USERNAME:test
DB_PASSWORD:test
DB_DIALECT:org.hibernate.dialect.PostgreSQL10Dialect
````

## Run postgres on docker
```
docker run --name postgres_server -e POSTGRES_PASSWORD=test -e POSTGRES_USER=test -e POSTGRES_DB=test --restart always -d postgres

```