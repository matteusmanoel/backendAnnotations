package app.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entities.Turma;
import app.repositories.TurmaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	private EntityManager entityManager;

	public TurmaService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private void verificarTurmaDuplicada(String nome, String turno) {
		if (!turmaRepository.findByNomeAndTurno(nome, turno).isEmpty()) {
			throw new RuntimeException("Já existe uma turma com este nome e turno cadastrada!");
		}
	}

	public String save(Turma turma) {
		verificarTurmaDuplicada(turma.getNome(), turma.getTurno());
		turmaRepository.save(turma);
		return "Turma salva com sucesso!";
	}

	public Turma findById(long idTurma) {
		return turmaRepository.findById(idTurma).orElseThrow(() -> new RuntimeException("Turma não encontrada!"));
	}

	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}

	public String update(Turma turma, long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Turma turmaExistente = turmaRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Turma não encontrada para atualização!"));

			turmaExistente.setNome(turma.getNome());
			turmaExistente.setAno(turma.getAno());
			turmaExistente.setSemestre(turma.getSemestre());
			turmaExistente.setTurno(turma.getTurno());
			turmaExistente.setCurso(turma.getCurso());

			entityManager.merge(turmaExistente);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
		return "Turma atualizada com sucesso!";
	}

	public String delete(long id) {
		if (!turmaRepository.existsById(id)) {
			throw new RuntimeException("Turma não encontrada para deletar!");
		}
		turmaRepository.deleteById(id);
		return "Turma deletada com sucesso!";
	}
}
