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

import app.entities.Professor;
import app.services.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired // instancia uma unica vez
	private ProfessorService professorService;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Professor professor) {

		try {
			// O que der certo

			String mensagem = this.professorService.save(professor);

			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Professor> findById(@PathVariable long id) {
		try {
			Professor professor = this.professorService.findById(id);
			return new ResponseEntity<>(professor, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Professor>> findAll() {
		try {
			List<Professor> professores = this.professorService.findAll();
			return new ResponseEntity<>(professores, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Professor professor, @PathVariable long id) {
		try {
			String mensagem = this.professorService.update(professor, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.professorService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
