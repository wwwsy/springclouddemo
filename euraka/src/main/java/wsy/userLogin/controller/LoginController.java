package wsy.userLogin.controller;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wsy.userLogin.controller.result.ResultAPI;
import wsy.userLogin.entity.User;
import wsy.userLogin.service.UserService;
import wsy.userLogin.util.SecurityPassword;

@RestController
@RequestMapping("/main")
public class LoginController {
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private UserService userService;
	@GetMapping("/user")
	public ResultAPI<?> Jedis(String name,String password,HttpSession session) throws NoSuchAlgorithmException{
		
		User user = userService.queryByName(name);
		if(user==null||user.getPassword().equals(SecurityPassword.String2Md5(password))){
			return ResultAPI.error("用户名或者密码错误");
			
		}
		session.setAttribute("user", user);
		return ResultAPI.ok(user);
	}
	
}
