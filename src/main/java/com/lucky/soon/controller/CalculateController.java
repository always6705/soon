package com.lucky.soon.controller;

import com.lucky.soon.model.EachResult;
import com.lucky.soon.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("test")
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
	@GetMapping(value = "/calculate")
	public void calculate(@RequestParam("date") String date, @RequestParam("orderNumber") Integer orderNumber) {
		calculateService.calculate(date, orderNumber);
	}
}
