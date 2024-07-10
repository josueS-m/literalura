# Literalura: Gestión de Libros y Autores

## Descripción del Proyecto

Literalura es una aplicación desarrollada en Java utilizando Spring Boot y Spring Data JPA, diseñada para gestionar información sobre libros y autores. La aplicación permite realizar consultas a una API externa (Gutendex) para obtener datos de libros y autores, almacenarlos en una base de datos PostgreSQL y proporcionar diversas funcionalidades interactivas para el usuario.

## Funcionalidades

- **Buscar libro por título**: Permite buscar un libro por su título y registrar la información del libro y su autor en la base de datos.
- **Listar libros registrados**: Muestra una lista de todos los libros registrados en la base de datos.
- **Listar autores registrados**: Muestra una lista de todos los autores registrados en la base de datos junto con sus libros.
- **Listar autores vivos en un determinado año**: Muestra una lista de autores que estaban vivos en un año específico.
- **Listar libros por idioma**: Permite buscar libros por su idioma.
- **Mostrar estadísticas de libros por idioma**: Muestra estadísticas sobre la cantidad de libros en diferentes idiomas en la base de datos.
- **Generar estadísticas de descargas:** Utiliza DoubleSummaryStatistics para mostrar estadísticas detalladas sobre las descargas de los libros almacenados.
- **Top 10 libros más descargados**: Muestra los 10 libros más descargados basado en el número de descargas.
- **Buscar autor por nombre**: Permite buscar autores en la base de datos por su nombre, utilizando consultas que buscan coincidencias parciales en el nombre del autor.
- **Listar autores por rango de año de nacimiento**: Permite listar los autores según un rango de años de nacimiento especificado.
- **Listar autores por rango de año de fallecimiento**: Permite listar los autores según un rango de años de fallecimiento especificado.

## Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

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
        spring.datasource.url=jdbc:postgresql://localhost:tu_localhost/tu_base_de_datos
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña
        spring.jpa.hibernate.ddl-auto=update
        ```

3. **Compilar y ejecutar la aplicación:**
    ```bash    
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

Opciones adicionales:

    Generar estadísticas de descargas:
        Utiliza DoubleSummaryStatistics para mostrar estadísticas detalladas sobre las descargas de los libros almacenados.

    Top 10 libros más descargados:
        Muestra los 10 libros más descargados basados en el número total de descargas.

    Buscar autor por nombre:
        Permite buscar autores en la base de datos por su nombre utilizando consultas que buscan coincidencias parciales en el nombre del autor.

    Listar autores por rango de año de nacimiento:
        Permite listar los autores según un rango de años de nacimiento especificado.

    Listar autores por rango de año de fallecimiento:
        Permite listar los autores según un rango de años de fallecimiento especificado.
