package secondQuestion.repository;

import firstQuestion.connection.EntityManagerSingleton;
import org.hibernate.Transaction;
import secondQuestion.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CourseRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    public Course save(Course course){

        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            entityManager.persist(course);

            transaction.commit();

            return course;
        } catch (Exception e){
            transaction.rollback();
            return null;
        }
    }

    public Course findByName(String name){
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            String hql = "SELECT c FROM Course c WHERE c.name = :name";
            TypedQuery<Course> typedQuery = entityManager.createQuery(hql, Course.class);
            typedQuery.setParameter("name",name);
            Course singleResult = typedQuery.getSingleResult();

            transaction.commit();

            return singleResult;
        } catch (Exception e){
            transaction.rollback();
            return null;
        }
    }
}
