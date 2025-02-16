package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
