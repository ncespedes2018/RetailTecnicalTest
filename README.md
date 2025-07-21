
# 🛍️ Retail Technical Test

This is a **Spring Boot 3.5.3** application written in **Java 21**, built with **Gradle**, and integrated with **OpenAPI Generator 7.6.0**.

It exposes a REST API backed by an in-memory **H2 database**, is documented with **Swagger UI**, and can be run either **locally** or in a **Docker container**.

---

## 📦 Tech Stack

| Component                  | Version        |
|----------------------------|----------------|
| Java                       | 21             |
| Spring Boot                | 3.5.3          |
| Gradle (wrapper)           | 8.x+           |
| OpenAPI Generator Plugin   | 7.6.0          |
| SpringDoc OpenAPI          | 2.8.9          |
| Swagger Annotations        | 2.2.34         |
| Jackson Databind           | 2.19.1         |
| H2 Database (runtime)      | Embedded       |
| Testing                    | Spock 2.3 + Groovy 4.0.15 + JUnit 5 |

---

## 🧠 Run the Application

### Option 1️⃣ — Run Locally (without Docker)

#### 1. Requirements

- Java 21 JDK
- Internet access (for Gradle dependencies)
- Optional: `curl` or `Postman` for testing the API

#### 2. Build the app

```bash
  ./gradlew clean build
```

This will:
- Generate OpenAPI sources from `openapi/product-ordering.yaml`
- Compile the project
- Produce a `.jar` file in `build/libs/`

#### 3. Run the app

```bash
  java -jar build/libs/retail-technical-test-0.0.1-SNAPSHOT.jar
```

> Adjust the JAR name if needed – you can auto-complete it with `tab`.

#### 4. Access the API

- API base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

---

### Option 2️⃣ — Run with Docker 🐳

#### 1. Build the Docker image

```bash
  docker build -t retail-app:latest .
```

This:
- Uses Java 21 (build + runtime)
- Generates the JAR inside the container
- Outputs a slim runtime image using Alpine

#### 2. Run the container

```bash
  docker run -p 8080:8080 retail-app:latest
```

Or run in detached mode:

```bash
  docker run -d -p 8080:8080 --name retail-container retail-app:latest
```

#### 3. Access the app

Same as local:
- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`

---

## 🧪 Run Tests

```bash
  ./gradlew test
```

### ✅ Test & Coverage Frameworks Used

- `Spock` – Groovy-based BDD-style specifications
- `JUnit 5` – For test execution and orchestration
- `JaCoCo` – Code coverage analysis tool

### 📁 Report Paths

- **Test Results (HTML):**  
  `build/reports/tests/test/index.html`
 
- **Code Coverage Report (JaCoCo HTML):**  
  `build/reports/jacoco/test/html/index.html`




---

## 📁 Project Structure

```
.
├── build.gradle
├── settings.gradle
├── Dockerfile
├── README.md
├── openapi/
│   └── product-ordering.yaml
├── src/
│   ├── main/java/
│   ├── test/groovy/
│   └── ...
└── build/generated-sources/openapi/
```

---

## ⚙️ OpenAPI Code Generation

This project uses the `org.openapi.generator` Gradle plugin with:

- Interface-only generation (`interfaceOnly = true`)
- `useJakartaEe = true` for Spring Boot 3+
- Models suffixed with `DTO`
- Grouping APIs by tags

Generated sources are located in:

```
build/generated-sources/openapi/src/main/java
```

These are compiled automatically during `./gradlew build`.

---

## 🔚 Stopping and Cleaning Up (Docker)

```bash
    docker stop retail-container
    docker rm retail-container
    docker rmi retail-app
```

---
## Example Request

```bash
    curl -X 'POST' \
      'http://localhost:8080/api/products/order' \
      -H 'accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
        "salesByUnits": 0.4,
        "stockRatio": 0.6
    }'
```
## Expected Response (Example)

```json
    [
  {
    "id": 3,
    "name": "RAISED PRINT T-SHIRT",
    "score": 0.6492307692307692,
    "salesUnits": 80,
    "stock": {
      "S": 20,
      "L": 20,
      "M": 2
    }
  },
  {
    "id": 2,
    "name": "CONTRASTING FABRIC T-SHIRT",
    "score": 0.6307692307692307,
    "salesUnits": 50,
    "stock": {
      "S": 35,
      "L": 9,
      "M": 9
    }
  },
  {
    "id": 6,
    "name": "SLOGAN T-SHIRT",
    "score": 0.6123076923076923,
    "salesUnits": 20,
    "stock": {
      "S": 9,
      "L": 5,
      "M": 2
    }
  },
  {
    "id": 4,
    "name": "PLEATED T-SHIRT",
    "score": 0.6018461538461538,
    "salesUnits": 3,
    "stock": {
      "S": 25,
      "L": 10,
      "M": 30
    }
  },
  {
    "id": 5,
    "name": "CONTRASTING LACE T-SHIRT",
    "score": 0.6,
    "salesUnits": 650,
    "stock": {
      "S": 0,
      "L": 0,
      "M": 1
    }
  },
  {
    "id": 1,
    "name": "V-NECH BASIC SHIRT",
    "score": 0.4615384615384615,
    "salesUnits": 100,
    "stock": {
      "S": 4,
      "L": 0,
      "M": 9
    }
  }
]
```

## 📄 License

This project is intended solely for educational and technical evaluation purposes as part of a recruitment process.
