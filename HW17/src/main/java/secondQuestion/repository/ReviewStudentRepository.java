package secondQuestion.repository;

import firstQuestion.connection.EntityManagerSingleton;
import secondQuestion.model.ReviewStudent;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class ReviewStudentRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    public ReviewStudent save(ReviewStudent reviewStudent){

        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            entityManager.persist(reviewStudent);

            transaction.commit();
            return reviewStudent;
        } catch (Exception e){
            transaction.rollback();
            return null;
        }
    }

    public ReviewStudent findByName(String name){
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            String hql = "SELECT r FROM ReviewStudent r WHERE r.name = :name";
            TypedQuery<ReviewStudent> typedQuery = entityManager.createQuery(hql, ReviewStudent.class);
            typedQuery.setParameter("name",name);
            ReviewStudent singleResult = typedQuery.getSingleResult();

            transaction.commit();

            return singleResult;
        } catch (Exception e){
            transaction.rollback();
            return null;
        }
    }
}
