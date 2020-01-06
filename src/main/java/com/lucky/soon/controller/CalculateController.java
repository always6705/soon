package com.lucky.soon.controller;

import com.lucky.soon.model.EachResult;
import com.lucky.soon.model.Result;
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

	@PostMapping(value = "/testResult")
	public List<EachResult> testResult(@RequestBody Result result) {
		logger.info("------------------CalculateController-test------------------");
		return null;
	}

	@GetMapping(value = "/queryTest")
	public List<EachResult> queryTest() {
		logger.info("------------------CalculateController-test------------------");
		return calculateService.test();
	}

	@PostMapping(value = "/makeMoney")
	public void makeMoney(@RequestBody Result result) {
		calculateService.makeMoney(result);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/14 23:16
	 * -param: eachResult
	 * -Description: 插入每期数据
	 */
	@PostMapping(value = "/insertEachResult")
	public void insertEachResult(@RequestBody EachResult eachResult) {
		calculateService.insertEachResult(eachResult);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/25
	 * -param: createDate, orderNumber
	 * -Description: 查询结果
	 */
	@GetMapping(value = "/queryResult")
	public List<Result> queryResult(@RequestParam(value = "createDate", required = false) String createDate,
	                                @RequestParam(value = "orderNumber", required = false) Integer orderNumber) {

		return calculateService.queryResult(createDate, orderNumber);
	}

	/**
	 * -Author: Sandy
	 * -Date: 2019/12/25
	 * -param: createDate, orderNumber
	 * -Description: 查询每期结果
	 */
	@GetMapping(value = "/queryEachResult")
	public List<EachResult> queryEachResult(@RequestParam(value = "createDate", required = false) String createDate,
	                                        @RequestParam(value = "orderNumber", required = false) Integer orderNumber) {
		// test 111
		return calculateService.queryEachResult(createDate, orderNumber);
	}

	@GetMapping(value = "/getYearList")
	public List<String> getYearList() {
		return calculateService.getYearList();
	}

	// 1. 统计一年
	// 2. 统计每个月
	// 3. 搜索期数或者日期
	// 4. 针对每次查询结果, 需要显示对应的animal
	// 5. 对上一期为负, 根据最新一期buy的个数进行预判(是否考虑odds)?

	// 6. TODO 合并第一、二步操作：预测的时候, 顺带将 actual 保存; 在实际第二步 buyNum 时, 再 update 对应的数据
	// 7. TODO 针对第三步操作：若没进行预测, 已自动处理第一步'预测'操作
	// 8. TODO 针对重复性操作：每一步要兼容其他两步


}
