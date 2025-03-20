package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Professor;
import app.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	private void verificarEmailDuplicado(String email) {
		if (professorRepository.findByEmail(email).isPresent()) {
			throw new RuntimeException("Email já cadastrado!");
		}
	}

	private void verificarDominioEmail(String email) {
		if (email.toLowerCase().endsWith("@outlook.com")) {
			throw new RuntimeException("Domínio de e-mail não permitido.");
		}
	}

	public String save(Professor professor) {
		verificarEmailDuplicado(professor.getEmail());
		verificarDominioEmail(professor.getEmail());

		professorRepository.save(professor);
		return "Professor salvo com sucesso!";
	}

	public Professor findById(long idProfessor) {
		return professorRepository.findById(idProfessor)
				.orElseThrow(() -> new RuntimeException("Professor não encontrado!"));
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public String update(Professor professor, long id) {
		Professor professorExistente = professorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Professor não encontrado para atualização!"));

		professorExistente.setNome(professor.getNome());
		professorRepository.save(professorExistente); // Substitui entityManager.merge()

		return "Professor atualizado com sucesso!";
	}

	public String delete(long id) {
		professorRepository.deleteById(id);
		return "Professor deletado com sucesso!";
	}
}
