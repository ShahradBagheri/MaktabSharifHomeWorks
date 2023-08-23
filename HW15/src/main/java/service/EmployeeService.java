package service;

import model.Employee;

public interface EmployeeService {

    Employee signup(Employee employee);

    void update(Employee employee);

    void remove(Employee employee);

    Employee findById(long id);

    Employee findByUsername(String username);
}
