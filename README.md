PruebaTecD1 API

API REST desarrollada con Spring Boot para la gestión y autenticación de usuarios.

Tecnologías utilizadas

Java 17

Spring Boot

Encriptación de contraseñas con AES256

Documentación con Swagger / OpenAPI

Docker

Postman Collection

Git

Funcionalidades

Crear usuarios

Actualizar usuarios

Eliminar usuarios

Endpoint de login para autenticación

Validación de RFC (tax_id)

Validación de número telefónico

Encriptación de contraseñas usando AES256

Documentación automática con Swagger

Documentación de la API

Swagger está disponible en:

http://localhost:8080/swagger-ui/index.html

Ejecutar el proyecto
1. Ejecutar localmente

Compilar el proyecto:

mvnw.cmd clean package

Ejecutar la aplicación:

mvnw.cmd spring-boot

La API iniciará en:

http://localhost:8080

Ejecutar con Docker

Construir la imagen Docker:

mvnw.cmd spring-boot -Dspring-boot.build-image.imageName=pruebatecd1

Ejecutar el contenedor:

docker run -p 8080:8080 pruebatecd1

La API estará disponible en:

http://localhost:8080

Swagger:

http://localhost:8080/swagger-ui/index.html

Ejemplo de login

Endpoint:

POST /users/login

Body:

{
"tax_id": "AARR990101HHH",
"password": "H0l4"
}

Notas

Las contraseñas se almacenan usando encriptación AES256.

La contraseña se elimina del cuerpo de respuesta.

El atributo created_at se genera con la zona horaria de Madagascar.

El tax_id se valida con formato RFC.

El número telefónico acepta código de país y debe cumplir el formato requerido.

Autor

Rafael Romero Gómez