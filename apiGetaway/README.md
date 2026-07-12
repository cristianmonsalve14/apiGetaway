# apiGetaway

API Gateway de **Libro Digital**: enrutamiento, CORS y validación JWT en el borde.

## Puerto

http://localhost:8090

## Stack

- Java 21 / Spring Boot 4.1
- Spring Cloud Gateway (WebFlux)
- JWT (jjwt) — filtro `JwtAuthenticationFilter`

**No usa base de datos.**

## Configuración

1. Copiar `application-local.properties.example` → `application-local.properties`
2. `jwt.secret` **igual** que auth/academic/attendance

```sh
mvn spring-boot:run
```

## Rutas

| Predicado | Destino |
|-----------|---------|
| `/auth/**`, `/admin/**` | authService :8091 |
| `/students/**`, `/courses/**`, `/teachers/**`, `/subjects/**`, `/enrollments/**`, `/evaluations/**`, `/grades/**`, `/guardians/**` | academicService :8092 |
| `/sessions/**`, `/attendances/**`, `/annotations/**` | attendanceService :8093 |

## Seguridad

- Públicos: `POST /auth/login`, `POST /auth/refresh`, `OPTIONS` (CORS)
- Resto: header `Authorization: Bearer {token}` válido
- CORS único en gateway (`localhost:*`); los microservicios no exponen CORS al navegador

## Autores

- Cristian Monsalve
- Hector Olivares
