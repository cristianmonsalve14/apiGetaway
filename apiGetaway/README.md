# apiGetaway

API Gateway de la plataforma Libro Digital.

Microservicio encargado del enrutamiento centralizado hacia authService, academicService y attendanceService. Desarrollado con Spring Boot 4.0.5, Spring Cloud Gateway y Java 21.

## Puerto

http://localhost:8090

## Stack tecnológico
- Java 21
- Spring Boot 4.0.5
- Spring Cloud Gateway
- Spring Security
- Maven

## Instalación y ejecución
1. Clona este repositorio.
2. Configura la conexión a la base de datos y servicios en `src/main/resources/application.properties`.
3. Compila y ejecuta con:
   ```sh
   mvn clean spring-boot:run
   ```

## Autores
- Cristian Monsalve
- Hector Olivares

---
Este microservicio es parte del ecosistema Libro Digital. Más información y documentación general en el repositorio de infraestructura.