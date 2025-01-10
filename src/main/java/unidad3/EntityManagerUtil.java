package unidad3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// ejemplo de singleton muy básico y sencillo para EntityManager
// debería ser un Singleton de doblecomprobación

public class EntityManagerUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("jpa-hibernate-h2"); // Nombre de la unidad de persistencia

    // Equivalente a Session
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void shutdown() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
