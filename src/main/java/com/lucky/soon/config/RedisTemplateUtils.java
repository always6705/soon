package com.lucky.soon.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RedisTemplateUtils {

	@Resource
	private StringRedisTemplate stringRedisTemplateBean;
	private static StringRedisTemplate stringRedisTemplate;

	// 通过 @PostConstruct 注解, 注入 StringRedisTemplate bean
	@PostConstruct
	public void init() {
		stringRedisTemplate = this.stringRedisTemplateBean;
	}

	// 获取 stringRedisTemplate.opsForValue()
	public static ValueOperations getStringRedisTemplate() {
		return stringRedisTemplate.opsForValue();
	}
}
