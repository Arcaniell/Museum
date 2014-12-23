package com.itaSS.dao;

import org.hibernate.criterion.Criterion;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Set;

public interface BaseDao<T, E> {

    public List<T> getAll();
    public void create(T entity);
    public T read(E id);
    public void update(T entity);
    public void remove(T entity);
    public Set<T> getSpecEntity(CriteriaQuery cq);
    public Set<T> getSpecEntity(Set<Criterion> criterions);

}
