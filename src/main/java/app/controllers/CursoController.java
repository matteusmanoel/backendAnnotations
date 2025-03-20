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

import app.entities.Curso;
import app.services.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired // instancia uma unica vez
	private CursoService cursoService;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Curso curso) {

		try {
			// O que der certo

			String mensagem = this.cursoService.save(curso);

			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Curso> findById(@PathVariable long id) {
		try {
			Curso curso = this.cursoService.findById(id);
			return new ResponseEntity<>(curso, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Curso>> findAll() {
		try {
			List<Curso> cursos = this.cursoService.findAll();
			return new ResponseEntity<>(cursos, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Curso curso, @PathVariable long id) {
		try {
			String mensagem = this.cursoService.update(curso, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.cursoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
