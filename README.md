# Receipt Processor
This is the implementation of Receipt Processor API specification described here - https://github.com/fetch-rewards/receipt-processor-challenge

## Tech stack
- Java 17
- Spring Boot 3
- In-memory H2 DB (for convinience)
- Project Lombok - to reduce boilerplate code
- ModelMapper - to simplify object mapping and data transformation between different object models
- Springdoc OpenAPI - automatic generation of an OpenAPI specification (formerly known as Swagger) and a web UI for interacting with the API

## Highlights
- New rules can be added without changing of underlying service
- Points calculation is cached
- Added unit tests
- In-memory DB Web UI is available (see below)
- Web UI to test REST API endpoints is available too (see below)

## Installation
There is no need to install Java or Maven - only Docker is required.

Clone the repository:
```
git clone https://github.com/shushkevich/receipt-processor.git
```

Build the Docker Image:
```
docker build -t receipt-processor .
```

Run the Docker Container
```
docker run -p 8080:8080 receipt-processor
```

## Testing
REST API Documentation and Testing UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```

In-memory DB UI is available at:
```
http://localhost:8080/h2-console
```
Use the following values to connect to it:
```
JDBC URL: jdbc:h2:mem:receipts
User Name: sa
Password: <empty>
```

### Test data
Use Process Receipts endpoint to add new receipts:
```json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```

Use Get Points endpoint to get number of points awarded. For the receipt above it should return:
```json
{
  "points": 28
}
```
