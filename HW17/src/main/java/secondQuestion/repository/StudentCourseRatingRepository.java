package secondQuestion.repository;

import firstQuestion.connection.EntityManagerSingleton;
import secondQuestion.model.StudentCourseRating;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class StudentCourseRatingRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    public void save(StudentCourseRating studentCourseRating){

        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            entityManager.persist(studentCourseRating);

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }
}
