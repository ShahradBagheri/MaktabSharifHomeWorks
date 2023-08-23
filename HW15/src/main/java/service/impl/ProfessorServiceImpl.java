package service.impl;

import model.Professor;
import repository.ProfessorRepository;
import service.ProfessorService;
import util.ApplicationContext;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProfessorServiceImpl implements ProfessorService {

    EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    ProfessorRepository professorRepository = ApplicationContext.professorRepository;

    @Override
    public Professor signup(Professor professor) {

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            professorRepository.create(professor);
            transaction.commit();
            return professor;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(Professor professor) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            professorRepository.delete(professor);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Professor findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Professor professor = professorRepository.findById(id);
            transaction.commit();
            return professor;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Professor professor) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            professorRepository.update(professor);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Professor findByUsername(String username) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Professor professor = professorRepository.findByUsername(username);
            transaction.commit();
            return professor;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean existsById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Professor professor;
        try {
            transaction.begin();
            professor = professorRepository.findById(id);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }
        return professor != null;
    }
}
