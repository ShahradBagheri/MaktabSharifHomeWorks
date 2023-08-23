package repository;

import model.Employee;

import java.util.List;

public interface EmployeeRepository{

    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    Employee findByUsername(String username);
}
