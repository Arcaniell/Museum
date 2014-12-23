package com.itaSS.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFact {

    private static final String dbName = "MuseumJPAHib";
    private static EntityManagerFactory managerFact = null;

    public static EntityManagerFactory initFactory(String PerUniName) {
        managerFact = Persistence.createEntityManagerFactory(PerUniName);
        return managerFact;
    }

    public static EntityManager getEntityManager() {
        if (managerFact == null) {
            initFactory(dbName);
        }
        return managerFact.createEntityManager();
    }

    public static void close() {
        if (managerFact != null) {
            managerFact.close();
        }
    }

}
