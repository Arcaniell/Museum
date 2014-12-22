package com.itaSS.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseDao<T, E> {

    private Class<T> entityClass;

    protected BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    Session session;
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        em = EntityManagerFact.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        cq.select(entity);
        TypedQuery<T> q = em.createQuery(cq);
        List<T> result = q.getResultList();
        em.close();
        return result;
    }

    public void create(T entity) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public T read(E id) {
        em = EntityManagerFact.getEntityManager();
        T fetchedEntity = em.find(entityClass, id);
        em.close();
        return fetchedEntity;
    }

    public void update(T entity) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void remove(T entity) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    @SuppressWarnings("unchecked")
    public Set<T> getSpecEntity(CriteriaQuery cq) {
        em = EntityManagerFact.getEntityManager();
        TypedQuery<T> q = em.createQuery(cq);
        Set<T> result = new HashSet<>(q.getResultList());
        em.close();
        return result;
    }

    public Set<T> getSpecEntity(Set<Criterion> criterions) {
        session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        Set<T> result = new HashSet(criteria.list());
        session.close();
        return result;
    }

}
