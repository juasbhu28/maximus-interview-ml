# Documento de diseño

## Índice

- [Entendimiento - Casos de Uso](#entendimiento)
- [Alcance](#alcance)
- [Solución de Arquitectura](#solución-de-arquitectura)
  - [Endpoints - Tabla de contratos](#endpoints---tabla-de-contratos)
  - [Modelos de esquemas por Request y Response](#modelos-de-esquemras-por-request-y-response)
- [Esquema de la Base de Datos](#esquema-de-la-base-de-datos)
- [JAVA Dependencies](#java-dependencies)
- [Documentación](#documentación)
- [CI/CD](#cicd)
- [Decisiones](#decisiones)
- [Preguntas para el cliente](#preguntas-para-el-cliente)


Este proyecto trabajará su solución en base a una arquitectura por capas orientada al dominio, con el fin de separar las responsabilidades y tener un código más limpio y mantenible. La arquitectura se compone de las siguientes capas:

## Entendimiento

**Casos de Uso**:

La solución propuesta se basa en el desarrollo de un microservicio que agrupa las publicaciones por país de los usuarios basado en algunas palabras clave. El microservicio consta de tres endpoints, cada uno con una responsabilidad específica.

Los siguientes son los casos de uso que se implementaron en el microservicio:

1. Validar Autenticación en las Peticiones: Antes de procesar cualquier solicitud, se verifica si viene con el encabezado X-AUTH-USER para garantizar que solo usuarios autenticados puedan interactuar con el sistema.

2. Obtener Diccionario de Palabras por País: Los usuarios pueden solicitar un diccionario que mapea cada país a un conjunto específico de palabras clave relacionadas.

3. Clasificar Publicaciones Según el País: Al recibir una serie de publicaciones, el sistema las analiza para identificar y agruparlas por el país según las palabras clave encontradas en cada mensaje.

4. Manejo de Ambigüedad en la Clasificación: Si una publicación contiene palabras clave de más de un país, se clasifica en el país con el mayor número de coincidencias.

5. Generar Estadísticas de Publicaciones Procesadas: Se mantiene un registro de cuántas publicaciones se han procesado en cada petición, permitiendo a los usuarios consultar estadísticas generales sobre la actividad del sistema.

**Casos de uso no soportados**:
> [!IMPORTANT]
>
> **TODO: Corroborar con el cliente**
>
> - Agregar, modificar o eliminar palabras del diccionario por país.
> - Almacenar las publicaciones procesadas en una base de datos.
> - Almacenar las estadísticas de las publicaciones procesadas en una base de datos.

## Alcance

El alcance de este proyecto es desarrollar un microservicio que agrupa las publicaciones por país de los usuarios basado en algunas palabras clave. El microservicio consta de tres endpoints, cada uno con una responsabilidad específica.

## Solución de Arquitectura

La solución propuesta esta compuesta por tres endpoints en el microservicio, cada uno con una responsabilidad específica, como se describe a continuación:

- **dictionary-controller**: Servicio que retorna el diccionario de palabras por país.
  - **GET /dictionary**: Retorna el diccionario de palabras por país.

- **validate-controller**: Servicio que valida las publicaciones y las agrupa por país.
  - **POST /validate**: Valida las publicaciones y las agrupa por país.

- **stats-controller**: Servicio que muestra las estadísticas de las publicaciones procesadas.
  - **GET /stats**: Muestra las estadísticas de las publicaciones procesadas.

Para la implementación de los servicios se utilizó Spring Boot, y cada servicio se encuentrá en un módulo usando vertical Slicing y una arquitectura por capas orientada al dominio.

La imagen a continuación muestra el diagrama de la solución:

![Diagrama de Componentes](/docs/diagrama-de-componente.png)

### Endpoints - Tabla de contratos

| Endpoint | Metodo | Headers | Body | Response | Status |
|----------|--------|---------|------|----------|--------|
| /dictionary | GET | X-AUTH-USER | - | [dictionaryResponseDTO](#dictionaryresponsedto) | 200 |
| /validate | POST | X-AUTH-USER | [validateRequestDTO](#validaterequestdto) | [validateResponseDTO](#validateresponsedto) | 200 |
| /stats | GET | X-AUTH-USER | - | [statsResponseDTO](#statsresponsedto) | 200 |

### Modelos de esquemas por Request y Response

| Nombre | Descripción | Dicionario |
|--------|-------------|-------------|
| [dictionaryResponseDTO](#dictionaryresponsedto) | Modelo de datos de la respuesta del diccionario de palabras por país. | - **site**: País al que pertenece la palabra.  </br> - **words**: Palabras clave. Lista de strings|
| [validateRequestDTO](#validaterequestdto)  | Modelo de entrada de datos de las publicaciones a validar. | - **posts**: Mensajes de las publicaciones. Lista de strings |
| [validateResponseDTO](#validateresponsedto) | Modelo de datos de las publicaciones validadas al responder. | - **site**: País al que pertenece la palabra.  </br> - **total**: Número de publicaciones procesadas.  </br> - **posts**: Mensajes de las publicaciones. Lista de strings |
| [statsResponseDTO](#statsresponsedto) | Modelo de datos de las estadísticas de las publicaciones procesadas. | - **request**: Identificador único de la petición.  </br> - **posts**: Número de publicaciones procesadas.  |

#### dictionaryResponseDTO

```json
    [
        {
            "site": "co",
            "words": [
                "parce",
                "usted",
                "piscina"
            ]
        },
        {
            "site": "ar",
            "words": [
                "pibe",
                "vos",
                "pileta"
            ]
        }
    ]


```

##### validateRequestDTO

```json
    {
        "posts": [
            "hola parce, como esta usted?",
            "hola pibe, vos que tal?",
            "parce que tal la piscina"
        ]
    }
```

##### validateResponseDTO

```json
    {
        "co": {
            "total": 2,
            "posts": [
                "hola parce, como esta usted?",
                "parce que tal la piscina"
            ]
        },
        "ar": {
            "total": 1,
            "posts": [
                "hola pibe, vos que tal?"
            ]
        }
    }
```

##### statsResponseDTO

```json
    [
        {
            "request": "98b18df9-c33b-463f-80a7-7bd19e6b9b42",
            "posts": 3
        }
    ]
```

## Esquema de la Base de Datos

Para la persistencia de los datos, se utilizó H2 Database, una base de datos en memoria que no requiere de una configuración adicional. El esquema de la base de datos se compone de las siguientes tablas:

- **dictionary**: Tabla que almacena el diccionario de palabras por país.
  - id: Identificador único de la palabra.
  - site: País al que pertenece la palabra.
  - word: Palabra clave.

- **posts**: Tabla que almacena las publicaciones procesadas.
  - id: Identificador único de la publicación.
  - site: País al que pertenece la publicación.
  - post: Mensaje de la publicación.
  - request: Identificador único de la petición.

- **stats**: Tabla que almacena las estadísticas de las publicaciones procesadas.
  - id: Identificador único de la estadística.
  - request: Identificador único de la petición.
  - posts: Número de publicaciones procesadas.

**Diagrama de Base de Datos**:

![Diagrama de Base de Datos](/docs/diagrama-de-basedatos.jpeg)

## JAVA Dependencies

Para Java Spring Boot, se utilizó las siguientes dependencias:

- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Swagger**
- **Spring Boot Actuator**
- **Spring Boot DevTools**
- **Spring Boot Test**
- **Spring Boot Starter Test**
- **Mockito**

Para la validación de headers, se utilizó la dependencia:

- **Spring Security**

Para la generación de reportes de cobertura, se utilizó la dependencia:

- **Jacoco**

### Documentación

La documentación de la API se encuentra en Swagger, y se puede acceder a través de la siguiente URL:

TODO: add url

### CI/CD

Se utilizó GitHub Actions para la integración continua y despliegue continuo.

### Decisiones

- Se utilizó Spring Boot para la implementación de los servicios, ya que es un framework robusto y con una gran comunidad de desarrolladores.

- Se utilizó H2 Database para la persistencia de los datos, ya que es una base de datos en memoria y no requiere de una configuración adicional.

- Se utilizó Docker Compose para la orquestación de los servicios, ya que permite definir y ejecutar aplicaciones Docker de múltiples contenedores.

- Se utilizó Swagger para la documentación de la API, ya que es una herramienta que permite diseñar, construir, documentar y consumir servicios web RESTful.

- Se utilizó Jacoco para la generación de reportes de cobertura, ya que es una herramienta que permite medir la cobertura de código de las pruebas unitarias.

- Se utilizó Spring Security para la validación de headers, ya que es un módulo de Spring que proporciona autenticación y autorización a nivel de solicitud HTTP.

- Se utilizó arquitectura HExs para la implementación de los servicios, ya que es una arquitectura que permite la separación de las capas de la aplicación.

- Se utilizo github actions para la integración continua y despliegue continuo, ya que es una herramienta que permite automatizar el proceso de desarrollo de software.

### Preguntas para el cliente

- Las peticiones de los usuarios deben ser autenticadas con un usuario y contraseña? o solo con el header X-AUTH-USER? o una combinación de ambos? dónde en el valor del header X-AUTH-USER se envía el usuario y contraseña o un JWT? Y la validación de identidad la hago hace el mismo servicio la hace un tercero?

- El diccionario de palabras por país, también debe tener un endpoint para agregar, modificar o eliminar palabras? o solo se consulta?

- Las publicaciones que se reciben, deben ser almacenadas en una base de datos? o solo se procesan y se muestran en el resultado?

- Las estadísticas de las publicaciones procesadas, deben ser almacenadas en una base de datos? o solo se muestran en el resultado? (Almacenar en una base de datos, permitiría tener un histórico de las estadísticas y poder consultarlas en cualquier momento)

- Las publicaciones que se reciben, pueden ser muy grandes? o siempre serán pequeñas? (Esto es importante para definir el tamaño de los campos en la base de datos)
