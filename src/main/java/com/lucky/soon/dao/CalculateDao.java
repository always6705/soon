package com.lucky.soon.dao;

import com.lucky.soon.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalculateDao {
	List<User> test();
}
