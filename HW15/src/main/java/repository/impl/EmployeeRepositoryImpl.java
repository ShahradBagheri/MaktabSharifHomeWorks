package repository.impl;

import model.Employee;
import model.Professor;
import repository.EmployeeRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Employee create(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        entityManager.merge(employee);
        return employee;
    }

    @Override
    public void delete(Employee employee) {
        entityManager.remove(employee);
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee findByUsername(String username) {
        String hql = "SELECT e FROM Employee e WHERE e.username = :username";
        TypedQuery<Employee> typedQuery = entityManager.createQuery(hql, Employee.class);
        typedQuery.setParameter("username",username);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Employee> findAll() {
        String hql = "SELECT e FROM Employee e";
        TypedQuery<Employee> typedQuery = entityManager.createQuery(hql, Employee.class);
        return typedQuery.getResultList();
    }
}
