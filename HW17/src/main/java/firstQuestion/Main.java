package firstQuestion;

import firstQuestion.service.impl.PersonServiceImpl;
import firstQuestion.service.impl.StudentServiceImpl;
import firstQuestion.model.Person;
import firstQuestion.service.impl.TeacherServiceImpl;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        PersonServiceImpl personService = new PersonServiceImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        TeacherServiceImpl teacherService = new TeacherServiceImpl();

        Person person = new Person();
        person.setFirstname("Shahrad");
        person.setLastname("Bagheri");
        person.setBirthdate(Date.valueOf("2000-11-26"));
        personService.save(person);

        person.setFirstname("Changed");
        person.setId(16L);
//
//        personService.update(person);

        personService.delete(person);
//
//        System.out.println(personService.loadById(16L));
//
//        System.out.println(personService.loadAll());

//        System.out.println(personService.contains(person));

//        System.out.println(personService.signUp("Someone","else"));

        ///////////////////////

//        Student student = new Student();
//        student.setFirstname("Shahrad");
//        student.setLastname("Bagheri");
//        student.setBirthdate(Date.valueOf("2000-11-26"));
//        student.setMajor("Math");
//        student.setAdmissionYear("2000");
//        student.setStudentId("1000");
//
//        studentService.save(student);

//        student.setLastname("Changed");
//        student.setId(1);

//        studentService.update(student);

//        System.out.println(studentService.contains(student));
//
//        System.out.println(studentService.contains(student));

//        System.out.println(studentService.loadById(4L));
//
//        System.out.println(studentService.loadAll());

//        System.out.println(studentService.signUp("signing up","signed up","123","2000"));

        //////////////

//        Teacher teacher = new Teacher();
//        teacher.setFirstname("a teacher");
//        teacher.setLastname("teacher lastname");
//        teacher.setTeacherId("5000");
//        teacher.setMajor("a major");
//        teacher.setTeacherTier(TeacherTier.ADVANCED);
//        teacher.setSalary(2000);
//        teacher.setBirthdate(Date.valueOf("2000-11-26"));

//        teacherService.save(teacher);

//        teacher.setId(10);
//        teacher.setFirstname("First");
//        teacherService.update(teacher);

//        teacher.setId();
//        System.out.println(teacherService.contains(teacher));

//        System.out.println(teacherService.loadById(13L));

//        System.out.println(teacherService.loadAll());

//        teacherService.delete(teacher);
    }
}
