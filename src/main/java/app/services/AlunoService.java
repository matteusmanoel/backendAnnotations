package app.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Aluno;
import app.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

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
		Aluno alunoExistente = alunoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Aluno não encontrado para atualização!"));

		alunoExistente.setNome(aluno.getNome());
		alunoRepository.save(alunoExistente); // Substitui entityManager.merge()

		return "Aluno atualizado com sucesso!";
	}

	public String delete(long id) {
		alunoRepository.deleteById(id);
		return "Aluno deletado com sucesso!";
	}
}
