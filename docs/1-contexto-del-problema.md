# Descripción del problema

## Contexto

Una empresa stá desarrollando una red social para todos las personas que trabajan alli.

Te han contratado para que desarrolles un proyecto que agrupe las publicaciones por pais de los usuarios basado en algunas palabras.

Sabrás la agrupación de los mensajes basados en un diccionario que retorna otro servicio, y cuando se encuentre dos o más palabras del diccionario:

Desarrollar el servicio como un endpoint independiente.

| Pais | Palabras |
|------|----------|
| co   | parce, usted, piscina |
| ar   | pibe, vos, pileta |

En caso que tenga palabras de mas de 1 pais, se deberá agrupar en el pais con mayor cantidad de palabras. Se pueden recibir varios mensajes al mismo tiempo y se debe mostrar
el total de los mensajes agrupados.

Verificar si la petición esta firmada con el header X-AUTH-USER, de lo contrario retornar un
código HTTP 403-Forbidden.

Agregar validaciones que creas necesarias y manejo de errores, excepciones, etc. Guardar
reporte de cuantas publicaciones se procesan por cada petición.

Ejemplo:

Endpoint que retorna el diccionario
GET /diccionario

Entrada:

```json
{
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
}

```

Endpoint que valida las publicaciones
POST /validate

Entrada:

```json
{
    "posts": [
        "hola parce, como esta usted?",
        "hola pibe, vos que tal?",
        "parce que tal la piscina"
    ]
}
```

Salida:

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

Endpoint que muestra las estadisticas
GET /stats

```json
[
    {
        "request": "98b18df9-c33b-463f-80a7-7bd19e6b9b42",
        "posts": 3
    }
]
```

[Volver al inicio](/README.md)

## Desafios

- Programa(s) en Java (preferiblemente spring boot) que solucione la necesidad anterior.
- Desarrolla el algoritmo de la manera más eficiente posible.
- Tests con cobertura del código al menos del 90% usando jUnit y/o Mockito.

[Volver al inicio](/README.md)

## Entregables

- Codigo fuente (en repositorio github).
- Instrucciones de como ejecutar el programa o API, en README del proyecto.
- Reporte (foto) del reporte de jacoco / cobertura del código).
- Documentación de la arquitectura del proyecto, swagger, componentes cloud, otros
componentes y otras decisiones tomadas.

[Volver al inicio](/README.md)