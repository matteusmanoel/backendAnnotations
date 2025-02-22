package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Curso;
import app.repositories.CursoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    private EntityManager entityManager;

    public CursoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void verificarCursoDuplicado(String nome) {
        if (!cursoRepository.findByNomeIgnoreCase(nome).isEmpty()) {
            throw new RuntimeException("Curso com este nome já está cadastrado!");
        }
    }

    public String save(Curso curso) {
        verificarCursoDuplicado(curso.getNome()); 
        cursoRepository.save(curso);
        return "Curso salvo com sucesso!";
    }

    public Curso findById(long idCurso) {
        return cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public String update(Curso curso, long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado para atualização!"));

            cursoExistente.setNome(curso.getNome()); 
            
            entityManager.merge(cursoExistente); 
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return "Curso atualizado com sucesso!";
    }

    public String delete(long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado para deletar!");
        }
        cursoRepository.deleteById(id);
        return "Curso deletado com sucesso!";
    }
}
