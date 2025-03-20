package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Turma;
import app.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

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
		Turma turmaExistente = turmaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Turma não encontrada para atualização!"));

		turmaExistente.setNome(turma.getNome());
		turmaRepository.save(turmaExistente); // Substitui entityManager.merge()

		return "Turma atualizado com sucesso!";
	}

	public String delete(long id) {
		if (!turmaRepository.existsById(id)) {
			throw new RuntimeException("Turma não encontrada para deletar!");
		}
		turmaRepository.deleteById(id);
		return "Turma deletada com sucesso!";
	}
}
