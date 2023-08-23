package ui;

import enumeration.ProfessorTier;
import enumeration.Role;
import model.*;

import util.ApplicationContext;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void run() {
        while (true) {

            System.out.println("1.Login\n2.Exit");

            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.println("Username");
                    String username = scanner.nextLine();

                    System.out.println("Password");
                    String password = scanner.nextLine();

                    User user = ApplicationContext.userService.signIn(username, password);

                    if (user == null)
                        System.out.println("Wrong username or password \nPlease try again");
                    else {
                        switch (user.getRole()) {
                            case STUDENT -> studentPanel(user);
                            case EMPLOYEE -> employeePanel(user);
                            case PROFESSOR -> professorPanel(user);
                        }
                    }
                }
                case "2" -> System.exit(0);
                default -> System.out.println("Not a valid option");
            }
        }
    }

    public static void studentPanel(User user) {
        while (true) {
            System.out.println("1.User Details\n2.View Courses\n3.Choose Course\n4.Remove Course\n5.Check scores\n6.Exit");

            switch (scanner.nextLine()) {
                case "1" -> studentUserDetails(user);
                case "2" -> showCourses();
                case "3" -> studentChooseCourse(user);
                case "4" -> studentRemoveCourse(user);
                case "5" -> studentCheckScores(user);
                case "6" -> System.exit(0);
                default -> System.out.println("Not a valid option");
            }
        }
    }

    public static void professorPanel(User user) {
        while (true) {
            System.out.println("1.User Details\n2.Submit score\n3.check paycheck\n4.Exit");

            switch (scanner.nextLine()) {
                case "1" -> professorUserDetails(user);
                case "2" -> professorSubmitScore(user);
                case "3" -> professorCheckPaycheck(user);
                case "4" -> System.exit(0);
                default -> System.out.println("Not a valid option");
            }
        }
    }


    public static void employeePanel(User user) {
        while (true) {
            System.out.println("1.Student settings\n2.Professor settings\n3.Employee settings\n4.Course settings\n5.User details\n6.Exit");

            switch (scanner.nextLine()) {
                case "1" -> employeeStudentSettings();
                case "2" -> employeeProfessorSettings();
                case "3" -> employeeEmployeeSettings();
                case "4" -> employeeCourseSettings();
                case "5" -> employeeUserDetails(user);
                case "6" -> System.exit(0);
                default -> System.out.println("Not a valid option");
            }
        }
    }

    //PROFESSOR

    public static void professorUserDetails(User user) {

        Professor professor = ApplicationContext.professorService.findByUsername(user.getUsername());
        System.out.println(professor);
    }


    public static void professorSubmitScore(User user) {

        Professor professor = ApplicationContext.professorService.findByUsername(user.getUsername());

        System.out.println("course_student id to set score");
        long courseStudentId;
        try {
            courseStudentId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!ApplicationContext.courseStudentService.professorOwnsCourseStudent(professor, courseStudentId)) {
            System.out.println("You dont own this course!");
            return;
        }

        System.out.println("what score to set");
        float score;
        try {
            score = Float.parseFloat(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (ApplicationContext.courseStudentService.validScore(score)) {
            CourseStudent courseStudent = ApplicationContext.courseStudentService.findById(courseStudentId);
            courseStudent.setScore(score);
            ApplicationContext.courseStudentService.update(courseStudent);
        } else {
            System.out.println("not a valid score");
        }
    }

    public static void professorCheckPaycheck(User user) {

        Professor professor = ApplicationContext.professorService.findByUsername(user.getUsername());

        System.out.println("Select a term");
        long term;
        try {
            term = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Course> courses = ApplicationContext.courseService.findByProfessorAndTerm(professor, term);

        System.out.println(professor + " " + courses + " final salary" + professor.calculateSalary(term));
    }

    //STUDENT

    public static void studentUserDetails(User user) {
        Student student = ApplicationContext.studentService.findByUsername(user.getUsername());
        System.out.println(student);
    }

    public static void showCourses() {
        System.out.println("Select a term");
        Set<Long> terms = ApplicationContext.courseService.getAllTerms();
        System.out.println(terms);

        long term;
        try {
            term = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!terms.contains(term)) {
            System.out.println("that term doesnt exist");
            return;
        }
        System.out.println(ApplicationContext.courseService.findByTerm(term));
    }

    public static void studentChooseCourse(User user) {

        Student student = ApplicationContext.studentService.findByUsername(user.getUsername());

        System.out.println("Select term");
        long term;
        try {
            term = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean has24Limit = ApplicationContext.courseStudentService.isStudentHighAvg(term, student);
        int currentUnits = ApplicationContext.courseStudentService.currentUnits(term, student);

        System.out.println("Select a course by id");
        long courseId;
        try {
            courseId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!ApplicationContext.courseService.existsByTermAndId(term, courseId)) {
            System.out.println("Course doesnt exist");
            return;
        }

        Course course = ApplicationContext.courseService.findById(courseId);

        if (ApplicationContext.courseStudentService.choseCourseThisTerm(course, student, term))
            System.out.println("already chosen this course this term");
        else if (ApplicationContext.courseStudentService.hasPassedCourse(course, student))
            System.out.println("you have already passed this course");
        else if (has24Limit) {
            if (currentUnits + course.getUnits() > 24) {
                System.out.println("You're over your unit limit");
            }
        } else if (currentUnits + course.getUnits() > 20)
            System.out.println("You're over your unit limit");
        else {
            CourseStudent courseStudent = ApplicationContext.courseStudentService.studentChoosing(student, course);
            if (courseStudent != null)
                System.out.println("Course with the id of " + courseStudent.getId() + " was added");
            else
                System.out.println("failed to add course");
        }
    }

    public static void studentCheckScores(User user) {

        Student student = ApplicationContext.studentService.findByUsername(user.getUsername());

        System.out.println(ApplicationContext.courseStudentService.findCourseByStudent(student));
    }

    public static void studentRemoveCourse(User user) {
        Student student = ApplicationContext.studentService.findByUsername(user.getUsername());

        System.out.println("course_student id to remove");
        long courseStudentId;
        try {
            courseStudentId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (ApplicationContext.courseStudentService.studentOwnsCourse(student, courseStudentId)) {
            ApplicationContext.courseStudentService.delete(ApplicationContext.courseStudentService.findById(courseStudentId));
            System.out.println("course deleted");
        } else
            System.out.println("You dont own this course_student id");
    }

    //Employee methods

    public static void employeeStudentSettings() {
        System.out.println("1.SingUp Student\n2.Remove Student\n3.Update Student");

        switch (scanner.nextLine()) {
            case "1" -> signUpStudent();
            case "2" -> removeStudent();
            case "3" -> updateStudent();
        }
    }

    public static void employeeProfessorSettings() {
        System.out.println("1.SingUp Professor\n2.Remove Professor\n3.Update Professor");

        switch (scanner.nextLine()) {
            case "1" -> signUpProfessor();
            case "2" -> removeProfessor();
            case "3" -> updateProfessor();
        }
    }

    public static void employeeEmployeeSettings() {
        System.out.println("1.SingUp Employee\n2.Remove Employee\n3.Update Employee");

        switch (scanner.nextLine()) {
            case "1" -> signUpEmployee();
            case "2" -> removeEmployee();
            case "3" -> updateEmployee();
        }
    }

    public static void employeeCourseSettings() {
        System.out.println("1.Submit Course\n2.Remove Course\n3.Update Course");

        switch (scanner.nextLine()) {
            case "1" -> submitCourse();
            case "2" -> removeCourse();
            case "3" -> updateCourse();
        }
    }

    public static void employeeUserDetails(User user) {
        Employee employee = ApplicationContext.employeeService.findByUsername(user.getUsername());
        System.out.println(employee.details());
    }

    public static void submitCourse() {
        System.out.println("Course name");
        String name = scanner.nextLine();

        System.out.println("Course term");
        long term;
        try {
            term = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Course unit");
        int unit;
        try {
            unit = Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Course professor id");
        long professorId;
        try {
            professorId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Professor professor = ApplicationContext.professorService.findById(professorId);
        if (professor == null) {
            System.out.println("Professor with that id doesnt exist");
            return;
        }
        Course course = ApplicationContext.courseService.submit(name, term, unit, professor);
        if (course != null)
            System.out.println("A course with id of " + course.getId() + " was created");
        else
            System.out.println("Failed to submit course");

    }

    public static void removeCourse() {
        System.out.println("Course id");
        long courseId;
        try {
            courseId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Course course = ApplicationContext.courseService.findById(courseId);

        if (course == null) {
            System.out.println("Course with that id doesnt exist");
            return;
        }
        ApplicationContext.courseService.remove(course);
    }

    public static void updateCourse() {
        System.out.println("Course id");
        long courseId;
        try {
            courseId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Course course = ApplicationContext.courseService.findById(courseId);

        if (course == null) {
            System.out.println("Course with that id doesnt exist");
            return;
        }

        System.out.println("Course name");
        String name = scanner.nextLine();

        System.out.println("Course term");
        long term;
        try {
            term = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Course unit");
        int unit;
        try {
            unit = Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Course professor id");
        long professorId;
        try {
            professorId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Professor professor = ApplicationContext.professorService.findById(professorId);
        if (professor == null) {
            System.out.println("Professor with that id doesnt exist");
            return;
        }

        course.setName(name);
        course.setTerm(term);
        course.setUnits(unit);
        course.setProfessor(professor);

        ApplicationContext.courseService.update(course);
    }

    public static void signUpEmployee() {
        System.out.println("Employee username");
        String username = scanner.nextLine();

        System.out.println("Employee password");
        String password = scanner.nextLine();

        System.out.println("Employee firstname");
        String firstname = scanner.nextLine();

        System.out.println("Employee lastname");
        String lastname = scanner.nextLine();

        System.out.println("Employee salary");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(Role.EMPLOYEE);

        Employee employee = new Employee();
        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setSalary(salary);
        employee.setUser(user);

        employee = ApplicationContext.employeeService.signup(employee);
        if (employee != null)
            System.out.println("employee with " + employee.getId() + "employee id was created\nUser with " + user.getId() + "user id was created");
        else {
            System.out.println("Failed to add the student");
        }
    }

    public static void removeEmployee() {
        System.out.println("Employee id");
        long professorId;
        try {
            professorId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Employee employee = ApplicationContext.employeeService.findById(professorId);

        if (employee == null) {
            System.out.println("Employee with that id doesnt exist");
            return;
        }
        ApplicationContext.employeeService.remove(employee);
    }

    public static void updateEmployee() {

        long id;
        try {
            System.out.println("Employee id");
            id = Long.parseLong(scanner.nextLine());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (ApplicationContext.employeeService.existsById(id)) {
            System.out.println("Employee with that id doesnt exist");
            return;
        }

        Employee employee = ApplicationContext.employeeService.findById(id);

        System.out.println("Employee firstname");
        String firstname = scanner.nextLine();

        System.out.println("Employee lastname");
        String lastname = scanner.nextLine();

        System.out.println("Employee salary");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Employee username");
        String username = scanner.nextLine();

        System.out.println("Employee password");
        String password = scanner.nextLine();

        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setSalary(salary);
        employee.getUser().setUsername(username);
        employee.getUser().setPassword(password);

        ApplicationContext.employeeService.update(employee);
    }

    public static void signUpProfessor() {
        System.out.println("Professor username");
        String username = scanner.nextLine();

        System.out.println("Professor password");
        String password = scanner.nextLine();

        System.out.println("Professor firstname");
        String firstname = scanner.nextLine();

        System.out.println("Professor lastname");
        String lastname = scanner.nextLine();

        System.out.println("Professor Type (1.Normal 2.Faculty)");
        ProfessorTier professorTier;
        try {
            int type = Integer.parseInt(scanner.nextLine());
            if (type == 1)
                professorTier = ProfessorTier.NORMAL;
            else if (type == 2)
                professorTier = ProfessorTier.FACULTY;
            else {
                System.out.println("Invalid type");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type");
            return;
        }

        double baseSalary = 0;
        if (professorTier == ProfessorTier.FACULTY) {
            System.out.println("Professor base salary");
            try {
                baseSalary = Double.parseDouble(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid number");
                return;
            }
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.PROFESSOR);

        Professor professor = new Professor();
        professor.setFirstname(firstname);
        professor.setLastname(lastname);
        professor.setProfessorTier(professorTier);
        professor.setBaseSalary(baseSalary);
        professor.setUser(user);

        professor = ApplicationContext.professorService.signup(professor);
        if (professor != null)
            System.out.println("professor with id of " + professor.getId() + " was created\nUser with id of " + user.getId() + " was created");
        else {
            System.out.println("Failed to add the professor");
        }
    }

    public static void removeProfessor() {
        System.out.println("Professor id");
        long professorId;
        try {
            professorId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Professor professor = ApplicationContext.professorService.findById(professorId);

        if (professor == null) {
            System.out.println("Professor with that id doesnt exist");
            return;
        }
        ApplicationContext.professorService.remove(professor);
    }

    public static void updateProfessor() {

        long id;
        try {
            System.out.println("Professor id");
            id = Long.parseLong(scanner.nextLine());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!ApplicationContext.professorService.existsById(id)) {
            System.out.println("professor with that id doesnt exist");
            return;
        }

        Professor professor = ApplicationContext.professorService.findById(id);


        System.out.println("professor firstname");
        String firstname = scanner.nextLine();

        System.out.println("professor lastname");
        String lastname = scanner.nextLine();

        System.out.println("Professor Type (1.Normal 2.Faculty)");
        ProfessorTier professorTier;
        try {
            int type = Integer.parseInt(scanner.nextLine());
            if (type == 1)
                professorTier = ProfessorTier.NORMAL;
            else if (type == 2)
                professorTier = ProfessorTier.FACULTY;
            else {
                System.out.println("Invalid type");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type");
            return;
        }

        double baseSalary = 0;
        if (professorTier == ProfessorTier.FACULTY) {
            System.out.println("Professor base salary");
            try {
                baseSalary = Double.parseDouble(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid number");
                return;
            }
        }

        System.out.println("Professor Username");
        String username = scanner.nextLine();

        System.out.println("Professor Password");
        String password = scanner.nextLine();

        professor.setFirstname(firstname);
        professor.setLastname(lastname);
        professor.setProfessorTier(professorTier);
        professor.setBaseSalary(baseSalary);
        professor.getUser().setUsername(username);
        professor.getUser().setPassword(password);

        ApplicationContext.professorService.update(professor);
    }

    public static void updateStudent() {

        long id;
        try {
            System.out.println("Student id");
            id = Long.parseLong(scanner.nextLine());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!ApplicationContext.studentService.existsById(id)) {
            System.out.println("Student with that id doesnt exist");
            return;
        }

        Student student = ApplicationContext.studentService.findById(id);


        System.out.println("Student firstname");
        String firstname = scanner.nextLine();

        System.out.println("Student lastname");
        String lastname = scanner.nextLine();

        System.out.println("Student username");
        String username = scanner.nextLine();

        System.out.println("Student password");
        String password = scanner.nextLine();

        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.getUser().setUsername(username);
        student.getUser().setPassword(password);

        ApplicationContext.studentService.update(student);
    }

    public static void removeStudent() {
        System.out.println("Student id");
        long studentId;
        try {
            studentId = Long.parseLong(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Student student = ApplicationContext.studentService.findById(studentId);

        if (student == null) {
            System.out.println("Student with that id doesnt exist");
            return;
        }
        ApplicationContext.studentService.remove(student);

    }

    public static void signUpStudent() {
        System.out.println("Student username");
        String username = scanner.nextLine();

        System.out.println("Student password");
        String password = scanner.nextLine();

        System.out.println("Student firstname");
        String firstname = scanner.nextLine();

        System.out.println("Student lastname");
        String lastname = scanner.nextLine();

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(Role.STUDENT);

        Student student = new Student();
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setUser(user);

        student = ApplicationContext.studentService.signup(student);
        if (student != null)
            System.out.println("Student with id of " + student.getId() + " was created\nUser with id of " + user.getId() + " was created");
        else {
            System.out.println("Failed to add the student");
        }
    }
}
