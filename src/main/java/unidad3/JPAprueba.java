package unidad3;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JPAprueba {
    public static void main(String[] args) {

        Pelicula pelicula1 = new Pelicula("Robocop", (short) 1989);
        Pelicula pelicula2 = new Pelicula("Terminator2", (short) 1992);


        var ent = Persistence.createEntityManagerFactory("jpa-hibernate-h2");
        EntityManager em = ent.createEntityManager();

        em.getTransaction().begin();
        em.persist(pelicula1);
        em.persist(pelicula2);
        em.getTransaction().commit();
    }

}
