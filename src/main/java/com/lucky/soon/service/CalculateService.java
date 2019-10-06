package com.lucky.soon.service;

import com.lucky.soon.model.EachResult;

import java.util.List;

public interface CalculateService {

    /**
     * -Author: Sandy
     * -Date: 2019/10/5 20:27
     * -param:
     * -Description:
     *
    */
    List<EachResult> test();

    /**
     * @Description 预测: 计算下期结果
     * @Param :
     * @Return :
     * @Author K1080077
     * @Date 2019/10/5
    */
	void calculate(String date, Integer orderNumber);
}
