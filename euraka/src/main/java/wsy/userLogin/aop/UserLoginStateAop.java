package wsy.userLogin.aop;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import wsy.userLogin.controller.result.ResultAPI;
import wsy.userLogin.util.JedisUtil;

@Component
@Aspect
public class UserLoginStateAop {
	@Autowired
	private JedisUtil jedisUtil;

	@Pointcut("execution(public * wsy.userLogin.controller.*.*(..)) && !execution (public * wsy.userLogin.controller.LoginController.*(..))")
	public void cut() {
	}

	@Around("cut()")
	public <T> ResultAPI<?> before(ProceedingJoinPoint point) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if ("token".equals(c.getName())) {
				token = c.getValue();
			}
		}
		if (token == null) {
			return ResultAPI.error(401, "没有登陆");
		}
		token = jedisUtil.get(token);
		if (token == null) {
			return ResultAPI.error(402, "登陆失效");
		}
		HttpSession session = JSON.parseObject(token, HttpSession.class);
		if (session == null) {
			return ResultAPI.error(403, "登陆异常");
		}
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return ResultAPI.error(403, "登陆异常");
		}
		return (ResultAPI<?>) point.proceed(point.getArgs());

	}
}
