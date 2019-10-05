package com.lucky.soon.controller;

import com.lucky.soon.model.User;
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
	public List<User> test() {
		logger.info("------------------CalculateController-test------------------");
		return calculateService.test();
	}

	@GetMapping("/hello")
	public String hello() {
		return null;
	}
}
