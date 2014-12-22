package com.itaSS.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFact {
    private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";
    private static final Configuration cfg = new Configuration().configure(CONFIG_FILE_LOCATION);
    private static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(cfg.getProperties());
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            try {
                sessionFactory = cfg.buildSessionFactory(builder.build());
            }
            catch (HibernateException e) {
                System.err.println("Failed create factory");
                e.printStackTrace();
                throw e;
            }
        }
    return sessionFactory;
    }

    public static void closeFactory() throws HibernateException {

        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    private SessionFact() {
    }
}
