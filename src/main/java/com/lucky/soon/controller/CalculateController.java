package com.lucky.soon.controller;

import com.lucky.soon.model.EachResult;
import com.lucky.soon.model.Result;
import com.lucky.soon.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/soon")
public class CalculateController {

	private final ReentrantLock reentrantLock = new ReentrantLock();

	private final static Logger logger = LoggerFactory.getLogger(CalculateController.class);

	@Resource
	private CalculateService calculateService;

//	@GetMapping("/testReentrantLock")
//	public void testReentrantLock() {
//		for (int i = 0; i < 10; i++) {
//				if (!reentrantLock.tryLock()) {
//					logger.error("{}, 没拿到锁", i);
//				} else {
//					try {
//					logger.info("{}, 拿到锁", i);
//					TimeUnit.SECONDS.sleep(2);
//					} catch (Exception e) {
//						logger.info("异常, {}", e.getMessage());
//					} finally {
//						logger.info("没拿到锁-释放锁, {}", i);
//						logger.info("释放锁, {}", i);
//						reentrantLock.unlock();
//					}
//				}
//		}
//	}

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



}
