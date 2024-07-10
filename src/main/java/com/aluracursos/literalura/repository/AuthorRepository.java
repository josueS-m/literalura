package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Authors, Long> {

    Optional<Authors> findByNombre(String nombre);
    List<Authors> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNullOrFechaDeFallecimientoGreaterThanEqualAndFechaDeNacimientoLessThanEqual(String yearOfBirth1, String yearOfDeath, String yearOfBirth2);
}