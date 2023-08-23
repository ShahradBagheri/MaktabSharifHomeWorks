package repository;

import model.Professor;

import java.util.List;

public interface ProfessorRepository {
    Professor create(Professor professor);

    Professor update(Professor professor);

    void delete(Professor professor);

    Professor findById(Long id);

    Professor findByUsername(String username);

    List<Professor> findAll();
}
