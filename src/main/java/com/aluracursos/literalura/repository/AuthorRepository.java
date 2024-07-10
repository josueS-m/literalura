package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Authors, Long> {

    Optional<Authors> findByNombre(String nombre);

    List<Authors> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNullOrFechaDeFallecimientoGreaterThanEqualAndFechaDeNacimientoLessThanEqual(String yearOfBirth1, String yearOfDeath, String yearOfBirth2);

    @Query("SELECT a FROM Authors a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Authors> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

    @Query("SELECT a FROM Authors a WHERE a.fechaDeNacimiento >= :yearStart AND a.fechaDeNacimiento <= :yearEnd")
    List<Authors> findByYearOfBirthRange(@Param("yearStart") String yearStart, @Param("yearEnd") String yearEnd);

    @Query("SELECT a FROM Authors a WHERE a.fechaDeFallecimiento >= :yearStart AND a.fechaDeFallecimiento <= :yearEnd")
    List<Authors> findByYearOfDeathRange(@Param("yearStart") String yearStart, @Param("yearEnd") String yearEnd);
}