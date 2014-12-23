package com.itaSS.dao.implementation;

import com.itaSS.dao.BaseDao;
import com.itaSS.dao.EntityManagerFact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public abstract class BaseDaoImp<T, E> implements BaseDao<T, E>{

    private Class<T> entityClass;

    protected BaseDaoImp(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    Session session;

//    @PersistenceContext(unitName = "MuseumJPAHib")
  EntityManager em = EntityManagerFact.getEntityManager();
//    EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        cq.select(entity);
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Transactional
    public void create(T entity) {
        em.persist(entity);
        em.flush();
    }

    @Transactional
    public T read(E id) {
        return em.find(entityClass, id);
    }

    @Transactional
    public void update(T entity) {
        em.merge(entity);
    }

    @Transactional
    public void remove(T entity) {
        em.remove(entity);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Set<T> getSpecEntity(CriteriaQuery cq) {
        TypedQuery<T> q = em.createQuery(cq);
        return new HashSet<>(q.getResultList());
    }

    @Transactional
    public Set<T> getSpecEntity(Set<Criterion> criterions) {
        session = SessionFact.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        Set<T> result = new HashSet<>(criteria.list());
        session.close();
        return result;
    }

}
