package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Authors;
import com.aluracursos.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByTituloContainsIgnoreCase(String titulo);
    List<Book> findByIdioma(String idioma);
    // Método para contar libros por idioma
    Long countByIdioma(String idioma);

    @Query("SELECT b FROM Book b JOIN b.autor a WHERE a.nombre ILIKE %:autor")
    List<Authors> findByAuthor(String autor);
}
