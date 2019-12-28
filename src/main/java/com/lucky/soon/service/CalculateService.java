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

	void makeMoney(Result result);

    /**
     * @Description 预测: 计算下期结果
     * @Param :
     * @Return :
     * @Author K1080077
     * @Date 2019/10/5
    */
	void calculate(String date, Integer orderNumber);


	/**
	 * @Description : calculate Result
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/7
	*/
	void calculateResult(Result result);

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:13
	 * -param: date, orderNumber, eachResult
	 * -Description: 插入每期数据
	*/
	void insertEachResult(EachResult eachResult);

    List<Result> queryResult(String createDate, Integer orderNumber);

	List<EachResult> queryEachResult(String createDate, Integer orderNumber);

}
