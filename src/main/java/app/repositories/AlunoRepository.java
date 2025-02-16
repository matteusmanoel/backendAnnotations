package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
