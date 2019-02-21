package wsy.userLogin.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wsy.userLogin.entity.User;

@RestController
@RequestMapping("/main")
public class MainController {
	@Autowired
	private JedisPool jedisPool;
	@GetMapping("/user")
	public User Jedis(){
		Jedis jedis = jedisPool.getResource();
		String key = UUID.randomUUID().toString().replace("-", "");
		System.out.println(key);
		User u = new User();
		u.setAge("12");
		u.setName("bb");
		String string = JSON.toJSONString(u);
		System.out.println(string);
		jedis.set(key, string);
		String value = jedis.get(key);
		User user = JSON.parseObject(value, User.class);
		System.out.println(user.getAge());
		return user;
	}
	
}
