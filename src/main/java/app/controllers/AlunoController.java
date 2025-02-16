package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Aluno;
import app.services.AlunoService;

@RestController
@RequestMapping("/api/Aluno")
public class AlunoController {

	@Autowired // instancia uma unica vez
	private AlunoService alunoService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Aluno Aluno) {

		try {
			// O que der certo

			String mensagem = this.alunoService.save(Aluno);

			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable long id) {
		try {
			Aluno aluno = this.alunoService.findById(id);
			return new ResponseEntity<>(aluno, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Aluno>> findByAll() {
		try {
			List<Aluno> alunos = this.alunoService.findAll();
			return new ResponseEntity<>(alunos, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Aluno Aluno, @PathVariable long id) {
		try {
			String mensagem = this.alunoService.update(Aluno, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.alunoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
