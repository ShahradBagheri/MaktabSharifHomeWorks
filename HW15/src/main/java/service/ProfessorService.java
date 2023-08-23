package service;

import model.Professor;

public interface ProfessorService {

    Professor signup(Professor professor);

    void remove(Professor professor);

    Professor findById(Long id);

    void update(Professor professor);

    Professor findByUsername(String username);
}
