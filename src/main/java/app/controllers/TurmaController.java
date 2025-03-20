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

import app.entities.Turma;
import app.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired // instancia uma unica vez
	private TurmaService turmaService;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Turma turma) {

		try {
			// O que der certo

			String mensagem = this.turmaService.save(turma);

			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> findById(@PathVariable long id) {
		try {
			Turma turma = this.turmaService.findById(id);
			return new ResponseEntity<>(turma, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Turma>> findAll() {
		try {
			List<Turma> turmas = this.turmaService.findAll();
			return new ResponseEntity<>(turmas, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Turma turma, @PathVariable long id) {
		try {
			String mensagem = this.turmaService.update(turma, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.turmaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
