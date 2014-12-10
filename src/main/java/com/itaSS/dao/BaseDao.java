package com.itaSS.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class BaseDao<T, E extends Serializable> {

    private Class<T> entityClass;

    protected BaseDao() {}

    protected BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(entityClass)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public E create(T entity) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        @SuppressWarnings("unchecked")
        E id = (E) session.save(entity);
        session.getTransaction().commit();
        return id;
    }

    public T read(E id) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        @SuppressWarnings("unchecked")
        T fetchedEntity = (T) session.get(entityClass, id);
        session.getTransaction().commit();
        return fetchedEntity;
    }

    public void update(T entity) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
    }

    public void delete(T entity) {
        Session session = SessionFact.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public List<T> getSpecEntity(Set<Criterion> criterions) {
        Session session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return criteria.list();
    }

}
