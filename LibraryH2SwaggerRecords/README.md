# Library API - H2 + Swagger + Records

## Requirements covered
- Spring Boot REST API
- H2 database
- Swagger UI
- Layered architecture
- record DTO classes
- validation
- global exception handler
- CRUD + rent endpoint

## Run
1. Open project in IntelliJ as Maven project
2. Run `LibraryApplication`
3. Open Swagger UI: `http://localhost:8080/swagger-ui.html`
4. Optional H2 console: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:librarydb`
   - User: `sa`
   - Password: empty

## Main endpoints
- GET `/api/books`
- GET `/api/books/{id}`
- POST `/api/books`
- PUT `/api/books/{id}`
- DELETE `/api/books/{id}`
- PATCH `/api/books/{id}/rent`
- GET `/api/authors`

## Sample POST body
```json
{
  "name": "Animal Farm",
  "category": "NOVEL",
  "authorId": 1,
  "state": "GOOD",
  "availableCopies": 4
}
```
