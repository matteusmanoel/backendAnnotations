package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}
