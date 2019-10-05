package com.lucky.soon.dao;

import com.lucky.soon.model.EachResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CalculateDao {
    List<EachResult> test();
}
