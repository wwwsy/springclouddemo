package wsy.userLogin.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component
public class JedisUtil {
	private final Integer EXPIRE_TIME = 900;
	
	@Autowired
	private JedisPool jedisPool;
	
	private Jedis jedis;
	@PostConstruct
	public void ini(){
		jedis = jedisPool.getResource();
	}
	
	public void set(String key,String value){
		jedis.set(key, value);
		jedis.expire(key, EXPIRE_TIME);	
	}
	public String get(String key){	
		jedis.expire(key, EXPIRE_TIME);
		return jedis.get(key);
	}
}
