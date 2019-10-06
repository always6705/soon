package com.lucky.soon.dao;

import com.lucky.soon.model.EachResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface CalculateDao {
    List<EachResult> test();

    /**
     * -Author: Sandy
     * -Date: 2019/10/5 22:59
     * -param:
     * -Description: 获取最后三期的num
     *
    */
    List<EachResult> getThreeResultByDesc();

    /**
     * -Author: Sandy
     * -Date: 2019/10/5 23:58
     * -param:
     * -Description: 根据tm获取num
     *
    */
    void getNumBy7Result(ArrayList<Integer> _7ResultList);
}
