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
- **Buscar autor por nombre**: Permite buscar un autor por su nombre. Muestra la información del autor y los títulos de sus libros.
- **Listar autores por rango de año de nacimiento**: Permite ingresar un rango de años y muestra los autores nacidos en ese rango.
- **Listar autores por rango de año de fallecimiento**: Permite ingresar un rango de años y muestra los autores fallecidos en ese rango.
  
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
7 - Generar estadísticas de descargas
8 - Top 10 libros más descargados
9 - Buscar autor por nombre
10 - Listar autores por rango de año de nacimiento
11 - Listar autores por rango de año de fallecimiento
0 - Salir
----------------------------------------------

Ejemplo de uso:

    Para ver un ejemplo de cómo utilizar la aplicación, sigue estos pasos:

    Buscar libro por título:
        Selecciona la opción 1.
        Ingresa el título del libro que deseas buscar (por ejemplo, "Moby Dick").

    Listar libros registrados:
        Selecciona la opción 2.

    Listar autores registrados:
        Selecciona la opción 3.

    Listar autores vivos en un determinado año:
        Selecciona la opción 4.
        Ingresa el año deseado (por ejemplo, 1900).

    Listar libros por idioma:
        Selecciona la opción 5.
        Ingresa el código del idioma (por ejemplo, es para español).

    Mostrar estadísticas de libros por idioma:
        Selecciona la opción 6.
        Ingresa el código del idioma (por ejemplo, en para inglés) o todos para ver estadísticas de todos los idiomas.

    Generar estadísticas de descargas:
        Selecciona la opción 7.

    Top 10 libros más descargados:
        Selecciona la opción 8.

    Buscar autor por nombre:
        Selecciona la opción 9.
        Ingresa el nombre del autor (por ejemplo, "Mark Twain").

    Listar autores por rango de año de nacimiento:
        Selecciona la opción 10.
        Ingresa el año de inicio y fin del rango (por ejemplo, 1800 y 1850).

    Listar autores por rango de año de fallecimiento:
        Selecciona la opción 11.
        Ingresa el año de inicio y fin del rango (por ejemplo, 1900 y 1950).
