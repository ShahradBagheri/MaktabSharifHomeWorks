package service.impl;

import model.Student;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import repository.StudentRepository;
import service.StudentService;
import util.ApplicationContext;
import util.Constant;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.passay.AllowedRegexRule.ERROR_CODE;

public class StudentServiceImpl implements StudentService {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final StudentRepository studentRepository = ApplicationContext.studentRepository;

    @Override
    public Student signUp(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Student studentOut = studentRepository.create(student);

            entityTransaction.commit();
            return studentOut;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public Student update(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Student studentOut = studentRepository.update(student);

            entityTransaction.commit();
            return studentOut;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public Student findById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Student studentOut = studentRepository.findById(id);

            entityTransaction.commit();
            return studentOut;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(1);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(1);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(1);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(1);

        return gen.generatePassword(8, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
    }

    @Override
    public Student login(String governmentId, String password) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Student student = null;
        try {
            entityTransaction.begin();

            student = studentRepository.findByGovernmentId(governmentId);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
        if (student != null)
            if (Objects.equals(student.getPassword(), password))
                return student;
        return null;
    }

    @Override
    public boolean socialIdValidation(String string) {
        return Pattern.matches("[0-9]{10}",string);
    }

    @Override
    public boolean governmentIdValidation(String governmentId) {
        return Pattern.matches("[0-9]{10}",governmentId);
    }

    @Override
    public boolean studentIdValidation(String studentId) {
        return Pattern.matches("[0-9]{8}",studentId);
    }

    @Override
    public boolean entranceYearValidation(int entranceYear) {
        LocalDate localDate = Constant.DATE;
        int year = localDate.getYear();
        return entranceYear <= year;
    }

    @Override
    public boolean socialIdExists(String socialId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            boolean socialIdExists = studentRepository.socialIdExists(socialId);

            entityTransaction.commit();
            return socialIdExists;
        } catch (Exception e) {
            entityTransaction.rollback();
            return true;
        }
    }

    @Override
    public boolean governmentIdExists(String governmentId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            boolean governmentIdExists = studentRepository.governmentIdExists(governmentId);

            entityTransaction.commit();
            return governmentIdExists;
        } catch (Exception e) {
            entityTransaction.rollback();
            return true;
        }
    }

    @Override
    public boolean studentIdExists(String studentId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            boolean studentIdExists = studentRepository.studentIdExists(studentId);

            entityTransaction.commit();
            return studentIdExists;
        } catch (Exception e) {
            entityTransaction.rollback();
            return true;
        }
    }

    @Override
    public boolean hasGraduated(Student student) {
        switch (student.getDegree()){
            case COLLAGE,MASTERS_NOT_CONNECTED -> {
                return Constant.DATE.getYear() >= student.getEntranceYear() + 2;
            }
            case BACHELOR_CONNECTED,BACHELOR_NOT_CONNECTED -> {
                return Constant.DATE.getYear() >= student.getEntranceYear() + 4;
            }
            case MASTERS_CONNECTED -> {
                return Constant.DATE.getYear() >= student.getEntranceYear() + 6;
            }
            case PHD_CONNECTED,PHD_NOT_CONNECTED,PROFESSIONAL_PHD -> {
                return Constant.DATE.getYear() >= student.getEntranceYear() + 5;
            }
        }
        return false;
    }
}
