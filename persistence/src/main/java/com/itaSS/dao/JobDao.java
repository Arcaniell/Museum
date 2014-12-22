package com.itaSS.dao;

import com.itaSS.entity.Job;

public class JobDao extends BaseDao<Job, Integer>{
    public JobDao() {
        super(Job.class);
    }

}
