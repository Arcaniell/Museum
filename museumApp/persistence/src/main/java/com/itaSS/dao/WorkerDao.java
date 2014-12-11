package com.itaSS.dao;

import com.itaSS.entity.Worker;

public class WorkerDao extends BaseDao<Worker, Integer> {
    public WorkerDao(Class<Worker> type) {
        super(type);
    }
}
