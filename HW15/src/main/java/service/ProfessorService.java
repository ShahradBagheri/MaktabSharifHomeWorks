package service;

import enumeration.ProfessorTier;
import model.Professor;
import model.Student;

public interface ProfessorService {

    Professor signup(Professor professor);

    void remove(Professor professor);

    Professor findById(Long id);

    void update(Professor professor);

    Professor findByUsername(String username);
}
