package com.itaSS.service.implementation;

import com.itaSS.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImp implements BaseService{

    protected final int many_results = 1;
    protected final int zero_result = 0;
    protected final String searchOptions = "\nEnter criteria for selecting: \n";

}
