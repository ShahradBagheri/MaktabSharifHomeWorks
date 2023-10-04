package firstQuestion.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import firstQuestion.model.Person;
import firstQuestion.model.Student;
import firstQuestion.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class SessionFactorySingleton {

    private final static Logger logger = LoggerFactory.getLogger(EntityManager.class);

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            logger.info("persisting the SessionFactory");

            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Teacher.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        logger.info("returning the SessionFactory");
        return LazyHolder.INSTANCE;
    }
}
