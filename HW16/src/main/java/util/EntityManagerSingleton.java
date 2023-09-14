package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private final static EntityManagerFactory emf;
    private final static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public static EntityManager getInstanceEM() {
        return em;
    }
}
