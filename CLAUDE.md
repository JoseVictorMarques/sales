# Claude Code Project Guide - sales (Backend)

## 🎯 Quick Start Context

This is a **Spring Boot car sales backend API** (AutoVenda) with JPA/Hibernate for data persistence and CORS support for Angular frontend.

### 📁 Project Structure
```
sales/
├── src/main/java/com/example/sales/
│   ├── controller/           # REST endpoints
│   │   ├── CarController.java
│   │   ├── BrandController.java
│   │   ├── ModelController.java
│   │   ├── TransmissionController.java
│   │   └── UserController.java
│   ├── business/             # Business logic
│   │   ├── CarBusiness.java
│   │   ├── BrandBusiness.java
│   │   └── TransmissionBusiness.java
│   ├── model/
│   │   ├── entities/         # JPA entities
│   │   │   ├── Car.java
│   │   │   ├── Brand.java
│   │   │   ├── Model.java
│   │   │   ├── ModelVersion.java
│   │   │   ├── Transmission.java
│   │   │   └── Person.java
│   │   ├── dtos/             # Data Transfer Objects
│   │   │   ├── CarDTO.java
│   │   │   └── ModelVersionDTO.java
│   │   └── enums/
│   │       ├── GenderEnum.java
│   │       └── TransmissionEnum.java
│   ├── repository/           # JPA repositories
│   │   ├── CarRepository.java
│   │   ├── BrandRepository.java
│   │   ├── ModelRepository.java
│   │   ├── TransmissionRepository.java
│   │   └── UserRepository.java
│   ├── config/               # Spring configuration
│   │   └── CorsConfig.java   # CORS for localhost:4200
│   ├── utils/
│   │   ├── Base64Utils.java
│   │   └── PasswordUtils.java
│   └── SalesApplication.java # Main entry point
├── prompts/                  # Implementation documentation
│   └── 202609042010_backend_car_listing_and_cors.md
├── pom.xml                   # Maven dependencies
└── mvnw / mvnw.cmd          # Maven wrapper
```

## ⚡ Before Starting a New Session

**DO THIS FIRST** instead of grepping the entire codebase:

1. **Read the prompts folder** - it has all context:
   ```bash
   cat prompts/202609042010_*.md
   ```
   This file explains:
   - What endpoints exist and their purpose
   - CORS configuration details
   - Data models and relationships
   - Integration with frontend

2. **Don't grep for general understanding** - prompts already document:
   - ❌ Don't: `grep -r "GET" src/main/java/com/example/sales/controller/`
   - ✅ Do: `cat prompts/202609042010_*.md` (see "Existing API Endpoints")

3. **Check current state only when needed**:
   - For specific bugs: `grep -r "specific-error" src/`
   - For method locations: Use Glob or Read tools
   - For implementation details: Read the specific file

## 🔌 API Endpoints Overview

See `prompts/202609042010_*.md` for complete details. Summary:

### Brand Management
```
GET  /brand/find-all        → List all brands
POST /brand/create          → Create new brand
```

### Model Management
```
GET  /model/list-models-by-brand/:id
POST /model/create-model
GET  /model/list-version-by-model/:id
POST /model/create-model-version
```

### Transmission Management
```
GET  /transmission/list-transmisison    (note: typo in endpoint)
POST /transmission/create-transmission
```

### Car Management (NEW - 2026-09-04)
```
GET  /car/list-cars         → List all cars
POST /car                   → Create new car
```

## 🗄️ Data Models

### Car Entity
```java
- id: Long (PK)
- description: String
- color: String
- manufacturingYear: Integer
- modelVersion: ModelVersion (FK)
- transmission: Transmission (FK)
- image: byte[] (Base64)
```

### CarDTO (API Response)
```java
- id, description, color, manufacturingYear
- modelVersionId, transmissionId (IDs instead of objects)
- imageBase64: String (for JSON transport)
```

### Related Entities
- **Brand**: id, name
- **Model**: id, name, brandId
- **ModelVersion**: id, version, modelId
- **Transmission**: id, name, type

## 🔐 CORS Configuration

**Configured for**:
- `http://localhost:3000` (Node)
- `http://localhost:4200` (Angular dev server)
- `http://localhost:8080` (Backend)

**Allowed Methods**: GET, POST, PUT, DELETE, OPTIONS
**Allowed Headers**: * (all)
**Credentials**: true
**Max Age**: 3600 seconds

See `src/main/java/com/example/sales/config/CorsConfig.java`

## 🚀 Running the Project

```bash
# Using Maven wrapper
mvnw spring-boot:run              # Windows: mvnw.cmd spring-boot:run

# Or with Maven installed
mvn spring-boot:run

# Build JAR
mvn clean package
java -jar target/sales-*.jar

# Access API: http://localhost:8080
```

## 📝 Common Tasks

### Add a new endpoint
1. Create method in appropriate `Business` class
2. Create/update `@RestController`
3. Add `@GetMapping` or `@PostMapping`
4. Return `ResponseEntity<T>`

Example:
```java
@GetMapping("/new-endpoint")
public ResponseEntity<List<CarDTO>> newEndpoint() {
    return ResponseEntity.ok(carBusiness.getNewData());
}
```

### Add database filtering
1. Create custom query in `CarRepository` (Spring Data JPA)
2. Add method in `CarBusiness` to call it
3. Update `CarController` to accept parameters
4. Frontend `CarService` will use the query params

### Add new entity
1. Create class in `model/entities/`
2. Add `@Entity` and `@Table` annotations
3. Create repository in `repository/`
4. Create DTO in `model/dtos/`
5. Add business logic in `business/`
6. Create controller in `controller/`

## 🛠️ Tech Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| Framework | Spring Boot | 3.x |
| ORM | Spring Data JPA | Latest |
| Database | (Default: H2 in-memory) | - |
| Build | Maven | 3.x |
| Java | Java | 17+ |
| Logging | SLF4J | Latest |

## 🔗 Related Projects

- **Frontend**: `../sales-ui/` (Angular 18 + Tailwind)
  - Makes HTTP requests to these endpoints
  - Expects CORS headers (configured ✅)
  - Sends `CarDTO` in responses

## ❓ Troubleshooting

**"Endpoint not found"**
→ Check controller mapping and method route in the relevant Controller class

**CORS errors from frontend**
→ Check `CorsConfig.java` and ensure localhost:4200 is allowed

**Data not persisting**
→ Check database configuration, usually uses H2 in-memory (resets on restart)

**Entity mapping errors**
→ Verify `@JoinColumn` and relationship annotations in entity classes

## 📚 Documentation

- **Implementation details**: `prompts/202609042010_*.md`
- **Frontend context**: `../sales-ui/prompts/202609042000_*.md`
- **Spring Boot docs**: https://spring.io/projects/spring-boot
- **Spring Data JPA**: https://spring.io/projects/spring-data-jpa

## ✅ Last Updated

- **Date**: 2026-09-04
- **Status**: ✅ Ready for development
- **Last Commit**: 2ccda15 (prompts documentation)

---

**Remember**: Check `prompts/` folder first when starting a new session to understand the implementation context! 📖
