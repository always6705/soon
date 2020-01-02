package com.lucky.soon.service.impl;

import com.lucky.soon.common.SysConstants;
import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.EachResult;
import com.lucky.soon.model.Result;
import com.lucky.soon.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CalculateServiceImpl implements CalculateService {

	private final static Logger logger = LoggerFactory.getLogger(CalculateServiceImpl.class);

	@Resource
	private CalculateDao calculateDao;

	@Override
	public List<EachResult> test() {
		return calculateDao.test();
	}

	@Override
	public void makeMoney(Result result) {
		// 1. 预测
		calculate(result.getCreateDate(), result.getOrderNumber());

		// 2. buy num
		buyNum(result);

		// 3. calculate each result
		List<EachResult> eachResultList = queryEachResult(result.getCreateDate(), result.getOrderNumber());
		if (!eachResultList.isEmpty()) {
			if (null == result.getEachResult()) {
				// TODO 多次计算 '预测'/'buy num'的情况
				result.setEachResult(eachResultList.get(0));
			}
			// TODO 传值时注意 createDate/orderNumber/odds 在哪个类上：多次计算 each result 的情况
			calculateResult(result);
		} else if (null != result.getEachResult()) {
			// 第一次计算 each result 的情况
			calculateResult(result);
		}
	}

	/**
	 * @ Description 预测: 计算下期结果
	 * @ Param :
	 * @ Return :
	 * @ Author K1080077
	 * @ createDate 2019/10/5
	 */
	@Override
	public void calculate(String createDate, Integer orderNumber) {
		logger.info("预测 --> 日期[{}], 期数[{}]", createDate, orderNumber);

		// 根据当前日期, 获取前4期所有num
		List<EachResult> threeResultByDescList = calculateDao.getThreeResultByDesc(createDate);

		// 获取前4期的num以及tm
		HashMap<String, Object> hashMap = getFourResultNum(threeResultByDescList);

		// 去除前4期后, 系统产生的num
		List<Integer> fourResultList = (List<Integer>) hashMap.get("allBuyNum");
		logger.info("---前4期num({}个): {}", fourResultList.size(), fourResultList);

		// 系统buy内容: system
		String expectNum = fourResultList.stream().map(String::valueOf).collect(Collectors.joining(","));
		logger.info("---日期{}, 系统产生{}个: {}", createDate, fourResultList.size(), expectNum);

		// 将计算的结果插入result表: 判断是否有记录, 有则更新, 无则插入 TODO 仅更新 [content_system, count_system]: buy内容与数量
		Result result = getResultByCreateDateAndOrderNumber(createDate, orderNumber);
		if (null != result) {
			calculateDao.updateResult(createDate, orderNumber, expectNum, fourResultList.size());
		} else {
			calculateDao.insertResult(createDate, orderNumber, expectNum, fourResultList.size());
		}

		// 4期tm
//		ArrayList<Integer> _7ResultList = new ArrayList<>((HashSet<Integer>) hashMap.get("_7ResultSet"));
//		logger.info("---前4期tm({}个): {}", _7ResultList.size(), _7ResultList);

		// 根据tm获取生肖所有num
//		List<Integer> numBy7Result = calculateDao.getNumBy7Result(createDate, _7ResultList);
//		logger.info("---计算4期生肖num({}个): {}", numBy7Result.size(), numBy7Result);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/10/5 23:29
	 * -param:
	 * -Description: 获取4期全部的num, 以及近4期tm生肖
	 */
	private HashMap<String, Object> getFourResultNum(List<EachResult> threeResultByDescList) {
		// 常量: 1-49
		List<Integer> allNum = new ArrayList<>(SysConstants.ALL_NUM);

		// 返回4期所有num, 以及tm
		HashMap<String, Object> hashMap = new HashMap<>();

		// 4期tm
		HashSet<Integer> _7Result = new HashSet<>();

		// 4期所有num
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

		logger.info("---前4期已出num({}个): {}", allResult.size(), allResult);

		// 4期num
		List<Integer> fourResultNumList = new ArrayList<>(allResult);

		// 去除前4期已有的num
		allNum.removeAll(fourResultNumList);

		hashMap.put("allBuyNum", allNum);
		hashMap.put("_7ResultSet", _7Result);

		return hashMap;
	}

	/**
	 * @ Description : buy num(且自动计算price)：content_actual, count_actual, unit_price,
	 * total_system = count_system * price, total_actual = count_actual * price
	 * @ Param :
	 * @ Return :
	 * @ Author K1080077
	 * @ Date 2019/10/7
	 */
	public void buyNum(Result result) {
		Result tempResult = getResultByCreateDateAndOrderNumber(result.getCreateDate(), result.getOrderNumber());

		// 若是buy num, 不走下面的if
		if (StringUtils.isEmpty(result.getContentActual())) {
			if (StringUtils.isEmpty(tempResult.getContentActual())) { // TODO 单纯'预测'
				result.setContentActual(tempResult.getContentSystem());
				// 设置默认price为 50: 之后通过计算或者配置文件更改
				result.setUnitPrice(50);
			} else if (!StringUtils.isEmpty(tempResult.getContentActual())) { // TODO 多次'预测'
				result.setContentActual(tempResult.getContentActual());
				// 从上次buy的价格中获取
				result.setUnitPrice(tempResult.getUnitPrice());
			}
		}

		// TODO 计算price: 判断上期price, 计算系统本期price; 实际的price手动输入
		calculateDao.updateResultForBuyNum(result.getCreateDate(), result.getOrderNumber(),
				result.getContentActual(), result.getContentActual().split(",").length, result.getUnitPrice());

	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 22:33
	 * -param: date
	 * -param: orderNumber
	 * -param: odds : pei lv
	 * -param: eachResult
	 * -Description: 该方法同时可处理历史数据和新数据
	 */
	@Override
	public void calculateResult(Result result) {
		EachResult eachResult = result.getEachResult();

		// 从result表中获取内容: 计算buy结果
		Result tempResult = getResultByCreateDateAndOrderNumber(result.getCreateDate(), result.getOrderNumber());

		// 插入每期数据
		insertEachResult(eachResult);

		tempResult.setEachResult(eachResult); // 供方法calculateResultTotal()使用
//		tempResult = calculateResultTotal(tempResult);
		calculateResultTotal(tempResult);

		// 设置odds
		if (null == tempResult.getOdds()) {
			tempResult.setOdds(result.getOdds());
		}
		if (null == tempResult.getOdds()) {
			tempResult.setOdds(42);
		}

		// update result
		calculateDao.updateResultForCalculate(tempResult);
	}

	// 单纯计算 total price
	private void calculateResultTotal(Result result) {
		result.setTotalResultSystem(null);
		result.setTotalResultActual(null);

		Integer rs7 = result.getEachResult().getRs7();
		Integer odds = result.getOdds();
		Integer unitPrice = result.getUnitPrice();

		// 系统
		String[] buyNumSystem = result.getContentSystem().split(",");
		for (int i = 0; i < buyNumSystem.length; i++) {
			if (Integer.parseInt(buyNumSystem[i]) == rs7) {
				result.setTotalResultSystem(unitPrice * odds - result.getTotalSystem());
				break;
			}
		}

		// 实际
		String[] buyNumActual = result.getContentActual().split(",");
		for (int i = 0; i < buyNumActual.length; i++) {
			if (Integer.parseInt(buyNumActual[i]) == rs7) {
				result.setTotalResultActual(unitPrice * odds - result.getTotalActual());
				break;
			}
		}

		if (null == result.getTotalResultSystem()) {
			result.setTotalResultSystem(-result.getTotalSystem());
		}

		if (null == result.getTotalResultActual()) {
			result.setTotalResultActual(-result.getTotalActual());
		}
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:13
	 * -param: date, orderNumber, eachResult
	 * -Description: 插入每期数据
	 */
	@Override
	public void insertEachResult(EachResult eachResult) {
		// 先删除每期记录
		calculateDao.deleteEachResultByDateAndOrderNumber(eachResult.getCreateDate(), eachResult.getOrderNumber());

		// insert result
		calculateDao.insertEachResult(eachResult);
	}

	@Override
	public List<Result> queryResult(String createDate, Integer orderNumber) {
		return calculateDao.queryResult(createDate, orderNumber);
	}

	@Override
	public List<EachResult> queryEachResult(String createDate, Integer orderNumber) {
		return calculateDao.queryEachResult(createDate, orderNumber);
	}

	// 根据 createDate、orderNumber 查找 Result
	private Result getResultByCreateDateAndOrderNumber(String createDate, Integer orderNumber) {
		return calculateDao.getResultByDateAndOrderNumber(createDate, orderNumber);
	}

	@Override
	public List<String> getYearList() {
		return calculateDao.getYearList();
	}
}
