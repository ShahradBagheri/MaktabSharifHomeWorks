package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class ApplicationContext {
    public static final CourseRepository courseRepository;
    public static final EmployeeRepository employeeRepository;
    public static final ProfessorRepository professorRepository;
    public static final StudentRepository studentRepository;
    public static final UserRepository userRepository;
    public static final CourseStudentRepository courseStudentRepository;

    public static final UserService userService;
    public static final StudentService studentService;
    public static final EmployeeService employeeService;
    public static final ProfessorService professorService;
    public static final CourseService courseService;
    public static final CourseStudentService courseStudentService;

    static {
        courseRepository = new CourseRepositoryImpl();
        employeeRepository = new EmployeeRepositoryImpl();
        professorRepository = new ProfessorRepositoryImpl();
        studentRepository = new StudentRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        courseStudentRepository = new CourseStudentRepositoryImpl();

        userService = new UserServiceImpl();
        studentService = new StudentServiceImpl();
        employeeService = new EmployeeServiceImpl();
        professorService = new ProfessorServiceImpl();
        courseService = new CourseServiceImpl();
        courseStudentService = new CourseStudentServiceImpl();
    }
}
