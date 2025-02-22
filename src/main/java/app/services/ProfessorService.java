package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Professor;
import app.repositories.ProfessorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	private EntityManager entityManager;

	public ProfessorService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

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
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			verificarEmailDuplicado(professor.getEmail());
			verificarDominioEmail(professor.getEmail());

			professor = entityManager.merge(professor);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
		return "Professor atualizado com sucesso!";
	}

	public String delete(long id) {
		professorRepository.deleteById(id);
		return "Professor deletado com sucesso!";
	}
}
