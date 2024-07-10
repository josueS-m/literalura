package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.Authors;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.BookData;
import com.aluracursos.literalura.model.Data;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.APIConsumption;
import com.aluracursos.literalura.service.ConvertData;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private Scanner scanner = new Scanner(System.in);
    private APIConsumption consumption = new APIConsumption();
    private ConvertData convertData = new ConvertData();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Authors> authors;

    private List<Book> books;

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu(){
        var option = -1;

        while(option != 0) {

            var menu = """
                    
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
                    """;
            System.out.println(menu);

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        searchBookByTitle();
                        break;
                    case 2:
                        registeredBooks();
                        break;
                    case 3:
                        registeredAuthors();
                        break;
                    case 4:
                        ListAuthorsForYear();
                        break;
                    case 5:
                        booksForLanguage();
                        break;
                    case 6:
                        showBookStatisticsByLanguage();
                        break;
                    case 7:
                        generateDownloadStatistics();
                        break;
                    case 8:
                        top10MostDownloadedBooks();
                        break;
                    case 9:
                        searchAuthorByName();
                        break;
                    case 10:
                        listAuthorsByBirthYearRange();
                        break;
                    case 11:
                        listAuthorsByDeathYearRange();
                        break;
                    case 0:
                        System.out.println("Cerrando Literalura...");
                        break;
                    default:
                        System.out.println("Opción Inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido");
                scanner.next();
            }
        }
    }

    private Optional<BookData> getDataBooks(String titleBook) {

        var json = consumption.getData(URL_BASE + "?search=" + titleBook.replace(" ", "+"));
        Data data = convertData.getData(json, Data.class);
        return data.results().stream()
                .filter(b -> b.titulo().toUpperCase().contains(titleBook.toUpperCase()))
                .findFirst();
    }

    //Metodo para buscar libros por titulo
    private void searchBookByTitle() {

        System.out.println("Ingrese el nombre del libro: ");
        var titleBook = scanner.nextLine();

        // Verificar si el libro ya existe en la base de datos
        Optional<Book> existingBook = bookRepository.findByTituloContainsIgnoreCase(titleBook);
        if (existingBook.isPresent()) {
            System.out.println("El libro ya está registrado: " + existingBook.get());
            return;
        }

        // Si el libro no existe, consultar la API
        try {
            Optional<BookData> bookData = getDataBooks(titleBook);
            if (bookData.isPresent() && !bookData.get().autor().isEmpty()) {
                Book book = new Book(bookData.get());

                // Verificar si el autor ya existe en la base de datos
                Authors author = book.getAutor();
                Optional<Authors> existingAuthor = authorRepository.findByNombre(author.getNombre());
                if (existingAuthor.isPresent()) {
                    book.setAutor(existingAuthor.get());
                } else {
                    authorRepository.save(author);
                    book.setAutor(author);
                }

                bookRepository.save(book);
                System.out.println(book);
            } else {
                System.out.println("No se encontró información del autor");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    //Metodo para mostrar libros registrados
    private void registeredBooks() {

        books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    //Metodo para mostrar autores registrados
    private void registeredAuthors() {

        authors = authorRepository.findAll();

        authors.forEach( author  -> {
            List<String> titulos = author .getBooks().stream()
                    .map(Book::getTitulo)
                    .collect(Collectors.toList());
            System.out.println("--------------------------" +
                    "\nAutor: " + author.getNombre() +
                    "\nFecha de nacimiento: " + author.getFechaDeNacimiento() +
                    "\nFecha de fallecimiento: " + author.getFechaDeFallecimiento() +
                    "\nLibros: " + titulos + "\n");
        });
    }

    //Metodo para mostrar autores vivos en un determinado año
    private void ListAuthorsForYear() {

        System.out.println("Ingrese el año para listar los autores vivos: ");
        String yearInput = scanner.nextLine();

        try {
            int year = Integer.parseInt(yearInput);
            List<Authors> authorsAlive = authorRepository.findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNullOrFechaDeFallecimientoGreaterThanEqualAndFechaDeNacimientoLessThanEqual(
                    String.valueOf(year), String.valueOf(year), String.valueOf(year));

            if (!authorsAlive.isEmpty()) {
                System.out.println("Autores vivos en el año " + year + ":");
                for (Authors author : authorsAlive) {
                    System.out.println(author.getNombre() + " (Nacido: " + author.getFechaDeNacimiento() + ", Fallecido: " + (author.getFechaDeFallecimiento() != null ? author.getFechaDeFallecimiento() : "Aún vivo") + ")");
                }
            } else {
                System.out.println("No se encontraron autores vivos en el año " + year);
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un año válido.");
        }

    }

    //Metodo para mostrar libros por idioma
    private void booksForLanguage() {

        var menuLanguage = """
                -----------------------------------------
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """;
        System.out.println(menuLanguage);
        var language = scanner.nextLine();

        try {
            List<Book> booksByLanguage = bookRepository.findByIdioma(language);
            if (!booksByLanguage.isEmpty()) {
                booksByLanguage.forEach(System.out::println);
            } else {
                System.out.println("No se encontraron libros en el idioma especificado");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar libros por idioma: " + e.getMessage());
        }
    }

    //Método para ver estadsticas de libro por idioma
    private void showBookStatisticsByLanguage() {

        var code = """
                ------------------------------------------                
                Códigos:
                    es - español
                    en - inglés
                    fr - francés
                    pt - portugués
                -------------------------------------------------------------------------------------------------------------
                Ingrese el código del idioma para ver las estadísticas, o 'todos' para ver estadísticas de todos los idiomas:
                """;
        System.out.println(code);
        String languageInput = scanner.nextLine().trim().toLowerCase();

        if (languageInput.equals("todos")) {
            showStatisticsForAllLanguages();
        } else {
            showStatisticsForSpecificLanguage(languageInput);
        }
    }

    //Método para las estadísticas por idioma en espeficifo
    private void showStatisticsForSpecificLanguage(String language) {

        long count = bookRepository.countByIdioma(language);
        System.out.println("Cantidad de libros en " + getLanguageName(language) + ": " + count);
    }

    //Metodo para las estadisticas de todos los idiomas
    private void showStatisticsForAllLanguages() {

        List<String> idiomas = Arrays.asList("es", "en", "fr", "pt"); // Añadir más idiomas si es necesario
        for (String idioma : idiomas) {
            showStatisticsForSpecificLanguage(idioma);
        }
    }
   
    // Método auxiliar para obtener el nombre del idioma
    private String getLanguageName(String languageCode) {

        switch (languageCode) {
            case "es":
                return "español";
            case "en":
                return "inglés";
            case "fr":
                return "francés";
            case "pt":
                return "portugués";
            default:
                return "desconocido";
        }
    }

    // Método para generar estadísticas de descargas
    private void generateDownloadStatistics() {

        DoubleSummaryStatistics stats = bookRepository.findAll().stream()
                .collect(Collectors.summarizingDouble(Book::getNumeroDescargas));

        System.out.println("Estadísticas de descargas:");
        System.out.println("Número total de descargas: " + stats.getSum());
        System.out.println("Promedio de descargas: " + stats.getAverage());
        System.out.println("Máximo de descargas: " + stats.getMax());
        System.out.println("Mínimo de descargas: " + stats.getMin());
    }

    private void top10MostDownloadedBooks() {
        List<Book> top10Books = bookRepository.findTop10ByOrderByNumeroDescargasDesc();
        System.out.println("Top 10 libros más descargados:");
        top10Books.forEach(System.out::println);
    }

    //Método para buscar autor por nombre
    private void searchAuthorByName() {

        System.out.println("Ingrese el nombre del autor: ");
        String nombreAutor = scanner.nextLine();

        List<Authors> autores = authorRepository.findByNombreContainingIgnoreCase(nombreAutor);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores con ese nombre.");
        } else {
            autores.forEach(author -> {
                List<String> titulos = author.getBooks().stream()
                        .map(Book::getTitulo)
                        .collect(Collectors.toList());
                System.out.println("--------------------------" +
                        "\nAutor: " + author.getNombre() +
                        "\nFecha de nacimiento: " + author.getFechaDeNacimiento() +
                        "\nFecha de fallecimiento: " + author.getFechaDeFallecimiento() +
                        "\nLibros: " + titulos + "\n");
            });
        }
    }

    //Método para buscar autor por año de nacimiento
    private void listAuthorsByBirthYearRange() {

        System.out.println("Ingrese el año de inicio del rango de nacimiento: ");
        String yearStart = scanner.nextLine();
        System.out.println("Ingrese el año de fin del rango de nacimiento: ");
        String yearEnd = scanner.nextLine();

        List<Authors> autores = authorRepository.findByYearOfBirthRange(yearStart, yearEnd);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores nacidos en ese rango de años.");
        } else {
            autores.forEach(author -> {
                List<String> titulos = author.getBooks().stream()
                        .map(Book::getTitulo)
                        .collect(Collectors.toList());
                System.out.println("--------------------------" +
                        "\nAutor: " + author.getNombre() +
                        "\nFecha de nacimiento: " + author.getFechaDeNacimiento() +
                        "\nFecha de fallecimiento: " + author.getFechaDeFallecimiento() +
                        "\nLibros: " + titulos + "\n");
            });
        }
    }

    //Método para buscar autor por año de fallecimiento
    private void listAuthorsByDeathYearRange() {

        System.out.println("Ingrese el año de inicio del rango de fallecimiento: ");
        String yearStart = scanner.nextLine();
        System.out.println("Ingrese el año de fin del rango de fallecimiento: ");
        String yearEnd = scanner.nextLine();

        List<Authors> autores = authorRepository.findByYearOfDeathRange(yearStart, yearEnd);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores fallecidos en ese rango de años.");
        } else {
            autores.forEach(author -> {
                List<String> titulos = author.getBooks().stream()
                        .map(Book::getTitulo)
                        .collect(Collectors.toList());
                System.out.println("--------------------------" +
                        "\nAutor: " + author.getNombre() +
                        "\nFecha de nacimiento: " + author.getFechaDeNacimiento() +
                        "\nFecha de fallecimiento: " + author.getFechaDeFallecimiento() +
                        "\nLibros: " + titulos + "\n");
            });
        }
    }
}
