package app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByNomeStartingWithOrEspecialidadeStartingWith(String nome, String especialidade);

    @Query("SELECT p FROM Professor p WHERE p.email NOT LIKE '%@gmail.com'")
    List<Professor> findByEmailNotGmail();

    Optional<Professor> findByEmail(String email);
}
