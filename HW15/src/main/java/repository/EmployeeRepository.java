package repository;

import model.Course;
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
