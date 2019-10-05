package com.lucky.soon.service.impl;

import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.EachResult;
import com.lucky.soon.model.NumAnimal;
import com.lucky.soon.service.CalculateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {

    @Resource
    private CalculateDao calculateDao;

    @Override
    public List<EachResult> test() {
//		return null;
        return calculateDao.test();
    }
}
