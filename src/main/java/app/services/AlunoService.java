package app.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entities.Aluno;
import app.repositories.AlunoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	private EntityManager entityManager;

	public AlunoService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private void validarNome(String nome) {
		String regex = "^(\\S+\\s+\\S+.*)$";
		if (!Pattern.matches(regex, nome)) {
			throw new RuntimeException("O nome deve conter pelo menos duas palavras.");
		}
	}

	private void verificarCpfDuplicado(String cpf) {
		if (alunoRepository.findByCpf(cpf).isPresent()) {
			throw new RuntimeException("CPF já cadastrado!");
		}
	}

	private void ajustarCadastroCompleto(Aluno aluno) {
		aluno.setCadastroCompleto(aluno.getTelefone() != null);
	}

	public String save(Aluno aluno) {
		validarNome(aluno.getNome());
		verificarCpfDuplicado(aluno.getCpf());
		ajustarCadastroCompleto(aluno);

		alunoRepository.save(aluno);
		return "Aluno salvo com sucesso!";
	}

	public Aluno findById(long idAluno) {
		return alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
	}

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public String update(Aluno aluno, long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			validarNome(aluno.getNome());
			ajustarCadastroCompleto(aluno);

			aluno = entityManager.merge(aluno);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
		return "Aluno atualizado com sucesso!";
	}

	public String delete(long id) {
		alunoRepository.deleteById(id);
		return "Aluno deletado com sucesso!";
	}
}
