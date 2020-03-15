package com.lucky.soon.controller;

import com.lucky.soon.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.lucky.soon.config.RedisTemplateUtils.getStringRedisTemplate;

@RestController
@RequestMapping(value = "/redis")
public class RedisTestController {

	private final static Logger logger = LoggerFactory.getLogger(RedisTestController.class);

	@Resource
	private StringRedisTemplate stringRedisTemplate;

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

	@GetMapping(value = "/redisSet")
//	@Cacheable
	public String redisSet(@RequestParam("key") String key, @RequestParam("value") String value) {
		logger.info("redisSet...");
		try {
//			stringRedisTemplate.opsForValue().set(key, value);
			getStringRedisTemplate().set(key, value);
			return "插入成功";
		} catch (Exception e) {
			logger.error("插入失败: {}", e.getMessage());
			return "插入失败";
		}
	}

}
