package com.lucky.soon.service.impl;

import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.User;
import com.lucky.soon.service.CalculateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {

	@Resource
	private CalculateDao calculateDao;

	@Override
	public List<User> test() {
		return calculateDao.test();
//		return null;
	}
}
