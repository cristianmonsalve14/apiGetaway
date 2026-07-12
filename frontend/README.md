# frontend

Módulo **Spring Boot** del ecosistema Libro Digital (scaffold reservado en el monorepo).

> La interfaz de usuario activa está en **`frontend-react/`** (React + TypeScript + Vite, puerto 8094).

## Propósito

Este módulo mantiene la estructura Maven/Spring Boot del proyecto. No contiene lógica de negocio ni entidades JPA en uso.

## Stack

- Java 21
- Spring Boot 4.1.0
- Spring Security, WebMvc (dependencias base)

## Ejecución (opcional)

```sh
mvn spring-boot:run
```

## UI del proyecto

Para la aplicación web:

```sh
cd ../frontend-react
npm install
npm run dev
```

## Autores

- Cristian Monsalve
- Hector Olivares
