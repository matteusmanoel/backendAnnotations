package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import app.entities.Turma;
import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByAnoBetween(int anoInicio, int anoFim);

    List<Turma> findBySemestreAndAno(int semestre, int ano);

    List<Turma> findByNomeAndTurno(String nome, String turno);

    @Query("SELECT t FROM Turma t WHERE LOWER(t.curso.nome) LIKE LOWER(CONCAT('%', :nomeCurso, '%'))")
    List<Turma> findByNomeCurso(@Param("nomeCurso") String nomeCurso);
}
