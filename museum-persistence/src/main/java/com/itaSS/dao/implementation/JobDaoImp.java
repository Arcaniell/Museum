package com.itaSS.dao.implementation;

import com.itaSS.entity.Job;
import org.springframework.stereotype.Repository;

@Repository
public class JobDaoImp extends BaseDaoImp<Job, Integer> {
    public JobDaoImp() {
        super(Job.class);
    }

}
