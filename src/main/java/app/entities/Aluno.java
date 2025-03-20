package app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min = 5, message = "O nome deve conter pelo menos duas palavras.")
	private String nome;
//	@Pattern(regexp = "^(\\S+\\s+\\S+.*)$", message = "O nome deve conter pelo menos duas palavras." // Erro "The annotation @Pattern is disallowed for this location" validar na service.

	@Column(unique = true, nullable = false)
	private String cpf;

	private String telefone;

	private Boolean cadastroCompleto;

	@ManyToOne(optional = true)
	@JoinColumn(name = "turma_id")
	private Turma turma;

}
