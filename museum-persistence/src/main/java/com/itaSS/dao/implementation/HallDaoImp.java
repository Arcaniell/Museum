package com.itaSS.dao.implementation;

import com.itaSS.dao.HallDao;
import com.itaSS.entity.Hall;
import org.springframework.stereotype.Repository;

@Repository
public class HallDaoImp extends BaseDaoImp<Hall, Integer>
        implements HallDao{
    public HallDaoImp() {
        super(Hall.class);
    }

}
