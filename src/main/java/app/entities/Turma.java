package app.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	private int ano;

	private int semestre;

	private String turno;

	@OneToMany(mappedBy = "turma")
	private List<Aluno> alunos;

	@ManyToOne(optional = false)
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;

	@ManyToMany
	@JoinTable(name = "turma_professor", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private List<Professor> professores;

}
