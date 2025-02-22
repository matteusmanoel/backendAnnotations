package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entities.Aluno;
import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByCpf(String cpf);

	List<Aluno> findByNomeStartingWith(String nome);

	List<Aluno> findByTelefoneContaining(String telefone);

	@Query("SELECT a FROM Aluno a WHERE a.turma.nome = :nomeTurma")
	List<Aluno> findByNomeDaTurma(@Param("nomeTurma") String nomeTurma);
}
