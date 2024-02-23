# Documento de desarrollo

Este documento describe el proceso de desarrollo del proyecto.

## 📖 Roadmap

- [x] Crear proyecto en Github
- [x] Crear carpeta de Documentación y Readme
- [x] Crear proyecto en Spring Boot con las dependencias definidas
- [x] Inciar gitflow
- [x] Crear Dockerfile
- [x] Crear file makefile
  - [x] make build
  - [x] make test
  - [x] make run
- [x] Crear CI/CD github actions. GitFlow procces
  - [x] Over features - Pass tests - % Covertura - Merge to develop
  - [x] Over develop - Pass test - Merge to main
- [x] Definición de tasking


[Volver al inicio](/README.md)

## 🚀 Desarrollo

Formato de Rama y de  Pull Request #[número-tarea]-[nombre] (ejemplo: #1-crear-dockerfile)

### Tasking

| Task | Description | Definition of Done |
| ---- | ----------- | ------------------- |
| #01-create-makefile| Crear Makefile - Dockerfile | Dockerfile creado y funcionando en localhost |
| #02-crear-ci-cd | Crear CI/CD github actions | CI/CD - push flow - Pr flow - checkstyle - covertura |
| #03_creating_config | Crear estructura de carpetas | Estructura de carpetas creada - auth JWT - Swagger |
| #04-crear-definiciones-dictionary | Crear entidades | Crear lógica de negocio para /dictionary |
| #05-crear-definiciones-validate | Crear entidades | Crear lógica de negocio para /validate |
| #06-crear-definiciones-stats | Crear entidades | Crear lógica de negocio para /stats |



### Cobertura

La cobertura del código se hace mediante jacoco y gradlew se encarga de evaluarlo en la tarea 
### Reporte

El reporte de cobertura se realizo con Jacoco y se encuentra en la carpeta `build/reports/jacoco/test/html/index.html`

### 📚 Referencias

- Arquitectura por capas orientada al dominio. [Link]()
- Git Flow. [Link]()

###   📖 Contribution and Roadmap

---

> [!Note]
> Coming soon
