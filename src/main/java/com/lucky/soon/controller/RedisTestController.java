package com.lucky.soon.controller;

import com.lucky.soon.util.JedisUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/redis")
public class RedisTestController {

	@PostMapping(value = "/set")
	public String set(@RequestParam("key") String key,
					  @RequestParam("value") String value,
					  @RequestParam("time") Integer time) {
		return JedisUtil.setObject(key, value, time);
	}

	@PostMapping(value = "/setLock")
	public String setLock(@RequestParam("key") String key,
						  @RequestParam("value") String value,
						  @RequestParam("time") Integer time) {
		return JedisUtil.setLock(key, value, time);
	}

	@GetMapping(value = "/get")
	public Object get(@RequestParam("key") String key) {
		return JedisUtil.getObject(key);
	}

}
