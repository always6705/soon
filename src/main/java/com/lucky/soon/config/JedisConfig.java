package com.lucky.soon.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis配置，项目启动注入JedisPool
 * http://www.cnblogs.com/GodHeng/p/9301330.html
 *
 * @author sandy
 * @date 2018/9/5 10:35
 */
@Configuration
public class JedisConfig {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(JedisConfig.class);

	@Value("${spring.jedis.host}")
	private String host;

	@Value("${spring.jedis.port}")
	private int port;

	@Value("${spring.jedis.timeout}")
	private int timeout;

	@Value("${spring.jedis.pool.max-active}")
	private int maxActive;

	@Value("${spring.jedis.pool.max-wait}")
	private int maxWait;

	@Value("${spring.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.jedis.pool.min-idle}")
	private int minIdle;

	@Bean
	public JedisPool redisPoolFactory() {
		try {
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxIdle(maxIdle);
			jedisPoolConfig.setMaxWaitMillis(maxWait);
			jedisPoolConfig.setMaxTotal(maxActive);
			jedisPoolConfig.setMinIdle(minIdle);
//			String pwd = StringUtil.isBlank(password) ? null : password;
			JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null);
			logger.info("初始化Redis连接池JedisPool成功!地址: " + host + ":" + port);
			return jedisPool;
		} catch (Exception e) {
			logger.error("初始化Redis连接池JedisPool异常:" + e.getMessage());
		}
		return null;
	}

}
