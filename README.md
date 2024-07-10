# Gestión de Libros y Autores

## Descripción del Proyecto

Este proyecto es una aplicación Java basada en Spring Boot que permite la gestión de libros y autores utilizando una base de datos PostgreSQL. El sistema permite buscar libros por título, listar libros registrados, listar autores registrados, listar autores vivos en un año específico, listar libros por idioma y mostrar estadísticas de libros por idioma.

## Funcionalidades

- **Buscar libro por título**: Permite buscar un libro por su título y registrar la información del libro y su autor en la base de datos.
- **Listar libros registrados**: Muestra una lista de todos los libros registrados en la base de datos.
- **Listar autores registrados**: Muestra una lista de todos los autores registrados en la base de datos junto con sus libros.
- **Listar autores vivos en un determinado año**: Muestra una lista de autores que estaban vivos en un año específico.
- **Listar libros por idioma**: Permite buscar libros por su idioma.
- **Mostrar estadísticas de libros por idioma**: Muestra estadísticas sobre la cantidad de libros en diferentes idiomas en la base de datos.

## Configuración del Entorno

### Prerrequisitos

- JDK 11 o superior
- Maven 3.6.3 o superior
- PostgreSQL 12 o superior

### Instalación

1. **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/tu-repositorio.git
    cd tu-repositorio
    ```

2. **Configurar la base de datos:**
    - Crear una base de datos PostgreSQL.
    - Actualizar las configuraciones de conexión a la base de datos en `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña
        spring.jpa.hibernate.ddl-auto=update
        ```

3. **Compilar y ejecutar la aplicación:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### Uso de la Aplicación

Una vez que la aplicación esté en funcionamiento, se presentará un menú con las siguientes opciones:

```plaintext
---------------Menú de Opciones---------------

1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
6 - Mostrar estadísticas de libros por idioma
0 - Salir
----------------------------------------------

Ejemplo de uso:

    Buscar libro por título:
        Seleccione la opción 1.
        Ingrese el título del libro.
        La aplicación buscará el libro en la API de Gutendex y registrará la información del libro y su autor en la base de datos.

    Listar libros registrados:
        Seleccione la opción 2.
        La aplicación mostrará una lista de todos los libros registrados en la base de datos.

    Listar autores registrados:
        Seleccione la opción 3.
        La aplicación mostrará una lista de todos los autores registrados junto con sus libros.

    Listar autores vivos en un determinado año:
        Seleccione la opción 4.
        Ingrese el año para listar los autores vivos en ese año.

    Listar libros por idioma:
        Seleccione la opción 5.
        Ingrese el código del idioma (es, en, fr, pt).

    Mostrar estadísticas de libros por idioma:
        Seleccione la opción 6.
        La aplicación mostrará las estadísticas de la cantidad de libros en diferentes idiomas.
