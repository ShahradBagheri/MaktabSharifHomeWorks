package service.impl;

import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;
import util.ApplicationContext;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository = ApplicationContext.employeeRepository;
    EntityManager entityManager = EntityManagerSingleton.getInstanceEM();


    @Override
    public Employee signup(Employee employee) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Employee employeeOut = employeeRepository.create(employee);

            entityTransaction.commit();
            return employeeOut;
        } catch (Exception e) {
            entityTransaction.rollback();
        }
        return null;
    }

    @Override
    public void update(Employee employee) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            employeeRepository.update(employee);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public void remove(Employee employee) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            employeeRepository.delete(employee);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public Employee findById(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Employee employee = employeeRepository.findById(id);
            transaction.commit();
            return employee;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Employee findByUsername(String username) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Employee employee = employeeRepository.findByUsername(username);
            entityTransaction.commit();
            return employee;
        } catch (Exception e){
            entityTransaction.rollback();
        }
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Employee employee;
        try {
            transaction.begin();
            employee = employeeRepository.findById(id);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }
        return employee != null;
    }
}

