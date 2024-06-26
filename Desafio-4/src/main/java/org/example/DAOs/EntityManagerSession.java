package org.example.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSession {

    private static EntityManagerFactory entityManagerFactory = null;

    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager(){
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("test");
        }
        if(entityManager == null){
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void closeEntityManager(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
