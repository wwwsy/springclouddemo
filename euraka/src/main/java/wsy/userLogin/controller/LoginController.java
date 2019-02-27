package wsy.userLogin.controller;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
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
import wsy.userLogin.util.JedisUtil;
import wsy.userLogin.util.SecurityPassword;

@RestController
@RequestMapping("/main")
public class LoginController {
	@Autowired
	private JedisUtil jedisUtil;
	@Autowired
	private UserService userService;
	@PostMapping("/user")
	public ResultAPI<?> Jedis(String name,String password,HttpSession session,HttpServletResponse response) throws NoSuchAlgorithmException{		
		User user = userService.queryByName(name);
		if(user==null||!user.getPassword().equals(SecurityPassword.String2Md5(password))){
			return ResultAPI.error("用户名或者密码错误");
		}
		session.setAttribute("user", user);
		String string = JSON.toJSONString(session);
		String token = UUID.randomUUID().toString().replace("-","");
		jedisUtil.set(token,string);
		System.out.println(token);
		Cookie c = new Cookie("token", token);
		c.setPath("/");
		response.addCookie(c);
		return ResultAPI.ok(user);
	}
	
}