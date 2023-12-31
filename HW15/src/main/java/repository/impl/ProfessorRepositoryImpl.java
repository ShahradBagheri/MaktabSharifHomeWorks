package repository.impl;

import model.Professor;
import repository.ProfessorRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Professor create(Professor professor) {
        entityManager.persist(professor);
        return professor;
    }

    @Override
    public Professor update(Professor professor) {
        entityManager.persist(professor);
        return professor;
    }

    @Override
    public void delete(Professor professor) {
        entityManager.remove(professor);
    }

    @Override
    public Professor findById(Long id) {
        return entityManager.find(Professor.class,id);
    }

    @Override
    public Professor findByUsername(String username) {
        String hql = "SELECT p FROM Professor p WHERE p.user.username = :username";
        TypedQuery<Professor> typedQuery = entityManager.createQuery(hql, Professor.class);
        typedQuery.setParameter("username",username);
        return typedQuery.getSingleResult();
    }

}
