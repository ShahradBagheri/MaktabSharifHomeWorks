package service.impl;

import model.Employee;
import model.Student;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class StudentServiceImpl implements StudentService {

    EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    StudentRepository studentRepository = new StudentRepositoryImpl();

    @Override
    public Student signup(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            studentRepository.create(student);
            transaction.commit();
            return student;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            studentRepository.delete(student);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student findById(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = studentRepository.findById(id);
            transaction.commit();
            return student;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            studentRepository.update(student);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student findByUsername(String username) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Student student = studentRepository.findByUsername(username);
            entityTransaction.commit();
            return student;
        } catch (Exception e){
            entityTransaction.rollback();
        }
        return null;
    }
}
