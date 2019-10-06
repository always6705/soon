package com.lucky.soon.service.impl;

import com.lucky.soon.common.SysConstants;
import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.EachResult;
import com.lucky.soon.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculateServiceImpl implements CalculateService {

	private final static Logger logger = LoggerFactory.getLogger(CalculateServiceImpl.class);

	@Resource
	private CalculateDao calculateDao;

	@Override
	public List<EachResult> test() {
//		return null;
		return calculateDao.test();
	}

	/**
	 * @Description 预测: 计算下期结果
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/5
	 */
	@Override
	public void calculate(String date, Integer orderNumber) {
		Integer buyPrice, entry, result;

		// date, 期数
//		LocalDate date = LocalDate.now();
//		logger.info("-----------------------------------------------------------------------");
//		logger.info("---当前日期: {}", date);

		List<EachResult> threeResultByDescList = calculateDao.getThreeResultByDesc();

		// 获取去除前三期的num以及tm
		HashMap<String, Object> hashMap = getThreeResultNum(threeResultByDescList);

		// 三期tm
		ArrayList<Integer> _7ResultList = new ArrayList<Integer>((HashSet<Integer>) hashMap.get("_7ResultSet"));
		logger.info("---前三期tm({}个): {}", _7ResultList.size(), _7ResultList);

		// 根据tm获取生肖所有num
		List<Integer> numBy7Result = calculateDao.getNumBy7Result(date, _7ResultList);
		logger.info("---计算三期生肖num({}个): {}", numBy7Result.size(), numBy7Result);

		// 去除生肖tm的num
		List<Integer> threeResultList = (List<Integer>) hashMap.get("allBuyNum");
		logger.info("---去除生肖前的num({}个): {}", threeResultList.size(), threeResultList);

		buyPrice = threeResultList.size() * 5;
		entry = 42 * 5;
		logger.info("---去除生肖前---下注金额: {}", buyPrice);
		logger.info("---去除生肖前---买中: {}", entry);
		logger.info("---去除生肖前---结果: {}", entry - buyPrice);

		logger.info("----------------------------------------");

		// 去除生肖num
		threeResultList.removeAll(numBy7Result);
		logger.info("---去除生肖后的num({}个): {}", threeResultList.size(), threeResultList);

		buyPrice = threeResultList.size() * 5;
		logger.info("-1--去除生肖后---下注金额: {}", buyPrice);
		logger.info("-1--去除生肖后---买中: {}", entry);
		logger.info("-1--去除生肖后---结果: {}", entry - buyPrice);
		logger.info("----------------------------------------");
		logger.info("-2--去除生肖后---下注金额: {}", buyPrice * 2.5);
		logger.info("-2--去除生肖后---买中: {}", entry * 2.5);
		logger.info("-2--去除生肖后---结果: {}", entry * 2.5 - buyPrice * 2.5);

		// 下注内容: system
		String expectNum = threeResultList.stream().map(String::valueOf).collect(Collectors.joining(","));

		// 将计算的结果插入result表: 判断是否有记录, 有则更新, 无则插入
		calculateDao.insertResult(date, orderNumber, expectNum);

	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/10/5 23:29
	 * -param:
	 * -Description: 获取三期全部的num, 以及近三期tm生肖
	 */
	private HashMap<String, Object> getThreeResultNum(List<EachResult> threeResultByDescList) {
		// 常量: 1-49
		List<Integer> allNum = new ArrayList<>(SysConstants.ALL_NUM);

		// 返回buyNum, 以及tm
		HashMap<String, Object> hashMap = new HashMap<>();

		// tm
		HashSet<Integer> _7Result = new HashSet<>();

		HashSet<Integer> allResult = new HashSet<>();

		for (EachResult eachResult : threeResultByDescList) {
			allResult.add(eachResult.getRs1());
			allResult.add(eachResult.getRs2());
			allResult.add(eachResult.getRs3());
			allResult.add(eachResult.getRs4());
			allResult.add(eachResult.getRs5());
			allResult.add(eachResult.getRs6());
			allResult.add(eachResult.getRs7());

			// 记录tm, 以便获取生肖, 之后去除该生肖所有num
			_7Result.add(eachResult.getRs7());
		}

		logger.info("---前三期已出num({}个): {}", allResult.size(), allResult);

		// 三期num
		List<Integer> threeResultNumList = new ArrayList<>(allResult);

		// 去除前3期已有的num
		allNum.removeAll(threeResultNumList);

		hashMap.put("allBuyNum", allNum);
		hashMap.put("_7ResultSet", _7Result);

		return hashMap;
	}

}
