package com.lucky.soon.controller;

import com.lucky.soon.model.EachResult;
import com.lucky.soon.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/soon")
public class CalculateController {

	private final static Logger logger = LoggerFactory.getLogger(CalculateController.class);

	@Resource
	private CalculateService calculateService;

	@PostMapping(value = "/test")
	public List<EachResult> test() {
		logger.info("------------------CalculateController-test------------------");
		return calculateService.test();
	}

	@GetMapping("/hello")
	public String hello() {
		logger.info("-----------------------hello-----------------------");
		return null;
	}

	/**
	 * @Description 预测: 计算下期结果
	 * @Param []:
	 * @Return void:
	 * @Author sandy
	 * @Date 2019/10/5
	 */
	@PostMapping(value = "/calculate")
	public void calculate(@RequestParam("date") String date,
	                      @RequestParam("orderNumber") Integer orderNumber) {
		calculateService.calculate(date, orderNumber);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:05
	 * -param: date
	 * -param: orderNumber
	 * -param: content
	 * -param: price
	 * -Description: buy num
	 */
	@PostMapping(value = "/buyNumber")
	public void buyNum(@RequestParam("date") String date,
	                   @RequestParam("orderNumber") Integer orderNumber,
	                   @RequestParam("content") String content,
	                   @RequestParam("price") Integer price) {
		calculateService.buyNum(date, orderNumber, content, price);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:05
	 * -param: date
	 * -param: orderNumber
	 * -param: odds: pei lv
	 * -param: eachResult
	 * -Description:
	 */
	@PostMapping(value = "/result")
	public void result(@RequestParam("date") String date,
	                   @RequestParam("orderNumber") Integer orderNumber,
	                   @RequestParam("odds") Integer odds,
	                   @RequestBody EachResult eachResult) {
		calculateService.result(date, orderNumber, odds, eachResult);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:16
	 * -param: eachResult
	 * -Description: 插入每期数据
	 *
	*/
	@PostMapping(value = "/insertEachResult")
	public void insertEachResult(@RequestBody EachResult eachResult) {
		calculateService.insertEachResult(eachResult);

	}

	// 1. 统计一年
	// 2. 统计每个月
	// 3. 搜索期数或者日期
	// 4. 针对每次查询结果, 需要显示对应的生肖
	// 5. 对上一期为负, 根据最新一期buy的个数进行预判(是否考虑odds)?
}
