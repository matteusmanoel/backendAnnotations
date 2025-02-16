package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Aluno;
import app.repositories.AlunoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Service
public class AlunoService {
	@Autowired
	AlunoRepository alunoRepository;

	private EntityManager entityManager;

	public AlunoService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public String save(Aluno aluno) {

		this.alunoRepository.save(aluno);
		return "Aluno salvo com sucesso!";

	}

	public Aluno findById(long idAluno) {

		Aluno aluno = this.alunoRepository.findById(idAluno).get();

		return aluno;

	}

	public List<Aluno> findAll() {

		List<Aluno> aluno = this.alunoRepository.findAll();

		return aluno;

	}

	public String update(Aluno aluno, long id) {

		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			// Atualizar o produto com o merge
			aluno = entityManager.merge(aluno); // merge atualiza a entidade no banco

			// Comitar a transação
			transaction.commit();
		} catch (RuntimeException e) {
			// Em caso de erro, fazer rollback
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
		return "Aluno atualizado com sucesso!";

	}

	public String delete(long id) {

		this.alunoRepository.deleteById(id);
		return "Aluno deletado com sucesso!";

	}
}
