package com.itaSS.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseDao<T, E extends Serializable> {

    private Class<T> entityClass;

    protected BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    Session session;

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(entityClass)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<T> result = criteria.list();
        session.close();
        return result;
    }

    public E create(T entity) {
        session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        @SuppressWarnings("unchecked")
        E id = (E) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public T read(E id) {
        session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        @SuppressWarnings("unchecked")
        T fetchedEntity = (T) session.get(entityClass, id);
        session.getTransaction().commit();
        session.close();
        return fetchedEntity;
    }

    public void update(T entity) {
        session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T entity) {
        session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public Set<T> getSpecEntity(Set<Criterion> criterions) {
        session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        Set<T> result = new HashSet<T>(criteria.list());
        session.close();
        return result;
    }

}
