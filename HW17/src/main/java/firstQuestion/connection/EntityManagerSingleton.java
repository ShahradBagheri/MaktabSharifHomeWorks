package firstQuestion.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private final static EntityManagerFactory emf;
    private final static EntityManager em;
    private final static Logger logger = LoggerFactory.getLogger(EntityManager.class);

    static {
        logger.info("persisting the EntityManager");
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public static EntityManager getInstanceEM() {
        logger.info("returning the EntityManager");
        return em;
    }
}
