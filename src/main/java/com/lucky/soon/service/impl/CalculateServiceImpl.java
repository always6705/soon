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

	/**
	 * @Description 预测: 计算下期结果
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/5
	 */
	@Override
	public void calculate(String date, Integer orderNumber) {
		logger.info("预测 --> 日期[{}], 期数[{}]", date, orderNumber);

		// 获取前4期所有num
		List<EachResult> threeResultByDescList = calculateDao.getThreeResultByDesc(date);

		// 获取前4期的num以及tm
		HashMap<String, Object> hashMap = getFourResultNum(threeResultByDescList);

		// 去除前4期num
		List<Integer> fourResultList = (List<Integer>) hashMap.get("allBuyNum");
		logger.info("---前4期num({}个): {}", fourResultList.size(), fourResultList);

		// 4期tm
//		ArrayList<Integer> _7ResultList = new ArrayList<>((HashSet<Integer>) hashMap.get("_7ResultSet"));
//		logger.info("---前4期tm({}个): {}", _7ResultList.size(), _7ResultList);

		// 根据tm获取生肖所有num
//		List<Integer> numBy7Result = calculateDao.getNumBy7Result(date, _7ResultList);
//		logger.info("---计算4期生肖num({}个): {}", numBy7Result.size(), numBy7Result);

		// 系统buy内容: system
		String expectNum = fourResultList.stream().map(String::valueOf).collect(Collectors.joining(","));

		// 将计算的结果插入result表: 判断是否有记录, 有则更新, 无则插入 TODO 仅更新 [content_system, count_system]: buy内容与数量
		Result result = calculateDao.getResultByDateAndOrderNumber(date, orderNumber);
		if (null != result) {
			calculateDao.updateResult(date, orderNumber, expectNum, fourResultList.size());
		} else {
			calculateDao.insertResult(date, orderNumber, expectNum, fourResultList.size());
		}

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
	 * @Description : buy num(且自动计算price)：content_actual, count_actual, unit_price,
	 * total_system = count_system * price, total_actual = count_actual * price
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/7
	 */
	@Override
	public void buyNum(String date, Integer orderNumber, String content, Integer price) {
		calculateDao.updateResultForBuyNum(date, orderNumber, content, content.split(",").length, price);
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
	public void result(String date, Integer orderNumber, Integer odds, EachResult eachResult) {
		// 从result表中获取内容: 计算buy结果
		Result result = calculateDao.getResultByDateAndOrderNumber(date, orderNumber);

		// 如果没数据, 则通过代码自动进行插入数据 TODO 若无数据, 则是计算以往数据; 若有数据, 则是正常流程(预测->buy->计算)
		if (null == result) {
			calculate(date, orderNumber);
			result = calculateDao.getResultByDateAndOrderNumber(date, orderNumber);
		} else {
			// 插入每期数据
			insertEachResult(eachResult);
		}

		result.setOdds(odds);
		result.setTotalResultSystem(null);
		result.setTotalResultActual(null);

		// 系统
		String contentSystem = result.getContentSystem();
		if (!StringUtils.isEmpty(contentSystem)) {
			String[] buyNumSystem = contentSystem.split(",");
			for (int i = 0; i < buyNumSystem.length; i++) {
				if (Integer.parseInt(buyNumSystem[i]) == eachResult.getRs7()) {
					result.setTotalResultSystem(result.getUnitPrice() * odds - result.getTotalSystem());
					break;
				}
			}
		}

		// 实际
		String contentActual = result.getContentActual();
		if (!StringUtils.isEmpty(contentActual)) {
			String[] buyNumActual = contentActual.split(",");
			for (int i = 0; i < buyNumActual.length; i++) {
				if (Integer.parseInt(buyNumActual[i]) == eachResult.getRs7()) {
					result.setTotalResultActual(result.getUnitPrice() * odds - result.getTotalActual());
					break;
				}
			}
		}

		if (null == result.getTotalResultSystem()) {
			result.setTotalResultSystem(-result.getTotalSystem());
		}

		if (null == result.getTotalResultActual()) {
			result.setTotalResultActual(-result.getTotalActual());
		}

		// update result
		calculateDao.updateResultForCalculate(result);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:13
	 * -param: date, orderNumber, eachResult
	 * -Description: 插入每期数据
	 *
	 */
	@Override
	public void insertEachResult(EachResult eachResult) {
		// 先删除每期记录
		calculateDao.deleteEachResultByDateAndOrderNumber(eachResult.getCreateDate(), eachResult.getOrderNumber());

		// insert result
		calculateDao.insertEachResult(eachResult);
	}
}
