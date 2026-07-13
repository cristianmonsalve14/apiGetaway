# Libro Digital — EV2

Monorepo / workspace de microservicios + frontend React para el caso semestral.

## Puertos

| Servicio | Puerto |
|----------|--------|
| apiGetaway | 8090 |
| authService | 8091 |
| academicService | 8092 |
| attendanceService | 8093 |
| frontend-react | 8094 |

## Arranque rápido

1. PostgreSQL con bases: `librodigital_auth`, `librodigital_academic`, `librodigital_attendance`.
2. En cada servicio Java, copiar:
   - `src/main/resources/application-local.properties.example`
   - a `application-local.properties` (no se versiona)
   - Misma `jwt.secret` en auth, academic, attendance y gateway.
3. Orden sugerido:
   ```sh
   cd authService && mvn spring-boot:run
   cd academicService && mvn spring-boot:run
   cd attendanceService && mvn spring-boot:run
   cd apiGetaway && mvn spring-boot:run
   cd frontend-react && npm run dev
   ```
4. UI: http://localhost:8094 — API vía gateway: http://localhost:8090

## Usuarios demo (password `test1234`)

| Usuario | Rol |
|---------|-----|
| `admin_colegio` | SUPER_ADMINISTRADOR |
| `admin_oficina` | ADMINISTRATIVO |
| `prof_castillo` | DOCENTE |
| `estudiante_demo` | ESTUDIANTE |
| `apoderado_demo` | APODERADO |

## Estructura del workspace

Este workspace agrupa varios repositorios/módulos (`authService`, `academicService`, `attendanceService`, `apiGetaway`, `frontend-react`, `infraestructura`).  
No es un único parent Maven: cada servicio se construye por separado. Ver `Libro digital -EV2.code-workspace` y docs en `infraestructura/` si está presente.

## Seguridad

- Secretos solo en `application-local.properties` (gitignored).
- El gateway valida JWT en el borde; los microservicios también.
- CORS solo en el gateway.
- Registro público deshabilitado; altas vía `/admin/users` (ADMINISTRADOR).

## Informe EP3 / Examen

Documentación de entrega en `infraestructura/informe-ep3/`:

- **`examen_transversal.pdf`**: informe único (arquitectura, BD, pruebas, repos, mejoras e innovación)
- `01`–`05`: fuentes parciales EP3 / respaldo
- `README_ENTREGA_EP3.md`: índice y checklist del ZIP
