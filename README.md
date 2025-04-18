# 🚚 Load and Booking Management System

This is a Spring Boot-based backend system designed for managing **Load** and **Booking** operations. It provides RESTful APIs for CRUD operations, enforces business rules, and ensures scalable and secure backend development.

---

## ✨ Features

- Create, update, and retrieve **Load** details
- Create and manage **Booking** records
- Manual DTO-to-Entity mapping using custom mappers
- Business logic encapsulated in service layer
- Error handling and response standardization
- JUnit 5 and Mockito-based unit tests
- PostgreSQL integration

---

## 🧰 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **JUnit 5 & Mockito**
- **Maven**

---

## 🏐 REST API Endpoints

### Load Endpoints

| Method | Endpoint            | Description          |
|--------|---------------------|----------------------|
| POST   | `/load`             | Create a new Load    |
| GET    | `/loads/{id}`       | Get Load by ID       |
| GET    | `/load`             | Get all Loads        |
| PUT    | `/load/{loadId}`    | Update load detail   |
| DELETE | `/load/{loadId} `   | Delete a load        |

### Booking Endpoints

| Method | Endpoint              | Description             |
|--------|-----------------------|-------------------------|
| POST   | `/booking`            | Create a new Booking    |
| GET    | `/booking/{id}`       | Get Booking by ID       |
| GET    | `/booking`            | Get all Bookings        |
| PUT    | `/booking/{bookingId}`| Update booking details  |
| DELETE | `/booking/{bookingId}`| Delete a booking        |

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/loadAndBooking/
│   │   ├── controllers/       # REST controllers
│   │   ├── dtos/              # Request and Response DTOs
│   │   ├── entities/          # Entity models
│   │   ├── repositories/      # JPA repositories
│   │   ├── services/          # Service interfaces and impl
│   │   ├── utils/             # DTO <-> Entity mappers
│   │   └── BackendApplication.java
│   └── resources/
│       └── application.properties
└── test/java/com/loadAndBooking/  # Unit tests
```

---

## 📅 Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/your-username/load-booking-system.git
cd load-booking-system
```

### 2. Configure PostgreSQL database
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/load_booking
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build and run the application
```bash
mvn clean install
mvn spring-boot:run
```

---

## 🚪 Lombok Setup (if not working)

- Make sure you have Lombok plugin installed in your IDE
- Enable Annotation Processing:
  - In IntelliJ: Preferences > Build, Execution, Deployment > Compiler > Annotation Processors > Check "Enable annotation processing"

---

## 🔮 Testing

Run all tests:
```bash
mvn test
```

Example Test Class: `LoadServiceImplTest.java`
- Uses Mockito for mocking dependencies
- Verifies business logic for `LoadService`

---

## 📁 Sample JSON Payloads

### Create Load
```json
{
  "shipperId": "shipper123",
  "truckType": "Open",
  "productType": "Steel",
  "weight": 1500,
  "facility": {
    "loadingPoint": "Delhi",
    "unloadingPoint": "Mumbai"
  }
}
```

### Create Booking
```json
{
  "loadId": "{load-id}",
  "carrierId": "carrier456",
  "status": "CONFIRMED"
}
```


