package com.itaSS.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFact {

    private static final String dbName = "MuseumJPAHib";
    private static EntityManagerFactory managerFact ;

    public static EntityManagerFactory initFactory(String PerUniName) {
        managerFact = Persistence.createEntityManagerFactory(PerUniName);
        return managerFact;
    }

    public static EntityManager getEntityManager() {
        if (managerFact != null) {
            return managerFact.createEntityManager();
        }
        System.err.println("Factory not created!");
        return null;
    }

    public static void close() {
        if (managerFact != null) {
            managerFact.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFact.initFactory(dbName);
        EntityManager entityManager = EntityManagerFact.getEntityManager();
        System.out.println(entityManager);
        EntityManagerFact.close();
    }

}
