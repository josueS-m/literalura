package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Authors autor;

    private String idioma;
    private Double numeroDescargas;

    public Book(){}

    public Book(BookData bookData) {
        this.titulo = bookData.titulo();
        AuthorsData firstAuthorData = bookData.autor().get(0);
        this.autor = new Authors(firstAuthorData.name(), firstAuthorData.birthYear(), firstAuthorData.deathYear());
        this.idioma = String.join(", ", bookData.idioma());
        this.numeroDescargas = bookData.numeroDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Authors getAutor() {
        return autor;
    }

    public void setAutor(Authors autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "\n---------- LIBRO ----------" +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor.getNombre() +
                "\nIdioma: " + idioma +
                "\nNumero de descargas: " + numeroDescargas + "\n" +
                "------------------------------------";
    }
}
