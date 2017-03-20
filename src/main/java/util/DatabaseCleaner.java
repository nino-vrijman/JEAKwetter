package util;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class DatabaseCleaner {

    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() throws SQLException {
        em.getTransaction().begin();

        em.createNativeQuery("DELETE FROM user_kweet").executeUpdate();
        em.createNativeQuery("DELETE FROM kweet_user").executeUpdate();
        em.createNativeQuery("DELETE FROM user_user").executeUpdate();
        em.createNativeQuery("DELETE FROM kweet").executeUpdate();
        em.createNativeQuery("DELETE FROM user").executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}