package repository;

import model.Employee;

public interface EmployeeRepository{

    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Employee employee);

    Employee findById(Long id);

    Employee findByUsername(String username);
}
