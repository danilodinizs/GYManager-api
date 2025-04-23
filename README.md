# GYManager

REST API for managing gym workout spreadsheets, developed with Spring Boot and modern architecture.

## About the Project

GYManager is a platform that allows users to register their own workout spreadsheets and customize them as they prefer. The project was developed with a focus on:

- **Content Organization**: Efficient workout management
- **Security**: JWT authentication for endpoint protection
- **Performance**: Optimized queries and caching
- **Scalability**: Growth-ready architecture

## Architecture

The project follows a layered architecture:

```
src/main/java/dev/danilo/gymanager/
├── config/         # Spring configurations
├── controller/     # REST endpoints
├── dto/            # Data Transfer Objects
├── entity/         # Database entities
├── enums/          # Enumerations
├── exceptions/     # Custom exceptions
├── mapper/         # Object mapping
├── repository/     # Spring Data access
└── service/        # Business logic
```


## Technologies

### Backend
- Java 21
- Spring Boot 3
- Spring Security
- JWT Authentication
- Lombok
- Bean Validation

### Database
- PostgreSQL 15
- Flyway migrations

### Tools
- Maven
- Swagger/OpenAPI

## Features

### Authentication
- User registration/login
- JWT token security
- Role-based access

### Spreadsheets
- Create/read/update/delete
- Bulk operations
- Custom validation

### Workouts
- Complete CRUD operations
- Spreadsheet associations
- Data integrity checks

### Exercises
- Detailed exercise tracking
- Workout relationships
- Custom configurations

## Requirements

- Java 21+
- PostgreSQL 15+
- Maven 3.8+

## Installation

1. Clone repository:
```bash
git clone https://github.com/your-repo/gymanager.git
```

2. Configure the PostgreSQL database in the `application.properties` file:
```properties
spring.datasource.url=jdbc:postgresql://localhost:8080/gymanager
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project:
```bash
./build.sh
```

4. Start the application:
```bash
./start.sh
```

Alternatively, you can run manually:

```bash
# Manual build
mvn clean package

# Manual execution
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Documentation

### Endpoints

#### Authentication
- POST `/auth/register` - Register new user
- POST `/auth/login` - User login

#### Spreadsheets
- POST `/v1/spreadsheet` - Create spreadsheet
- GET `/v1/spreadsheet` - List spreadsheets
- GET `/v1/spreadsheet/{id}` - Get spreadsheet by ID
- DELETE `/v1/spreadsheet/{id}` - Delete spreadsheet
- DELETE `/v1/spreadsheet/all` - Delete all spreadsheets
- PATCH `/v1/spreadsheet/{id}` - Update spreadsheet

#### Workouts
- POST `/v1/workout` - Create workout
- GET `/v1/workout` - List workouts
- GET `/v1/workout/{id}` - Get workout by ID
- DELETE `/v1/workout/{id}` - Delete workout
- DELETE `/v1/workout/all` - Delete all workouts
- PATCH `/v1/workout/{id}` - Update workout

#### Exercises
- POST `/v1/exercise` - Create exercise
- GET `/v1/exercise` - List exercises
- GET `/v1/exercise/{id}` - Get exercise by ID
- DELETE `/v1/exercise/{id}` - Delete exercise
- DELETE `/v1/exercise/all` - Delete all exercises
- PATCH `/v1/exercise/{id}` - Update exercise

## Contributing

1. Fork the project
2. Create a branch for your feature (git checkout -b feature/AmazingFeature)
3. Commit your changes (git commit -m 'Add some AmazingFeature')
4. Push to the branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

<!-- ## Versioning

We use [SemVer](http://semver.org/) for versioning. For available versions, see the [tags in this repository](https://github.com/your-username/flix/tags).
-->
## Authors

* **Danilo Diniz** - *Initial work*

<!--
## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
-->