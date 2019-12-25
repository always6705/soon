package com.lucky.soon.service;

import com.lucky.soon.model.EachResult;
import com.lucky.soon.model.Result;

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

	/**
	 * @Description : buy num
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/7
	*/
	void buyNum(String date, Integer orderNumber, String content, Integer price);

	/**
	 * @Description : result
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/7
	*/
	void result(EachResult eachResult);

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:13
	 * -param: date, orderNumber, eachResult
	 * -Description: 插入每期数据
	*/
	void insertEachResult(EachResult eachResult);

    List<Result> queryResult(String createDate, String orderNumber);
}
