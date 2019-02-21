package wsy.euraka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wsy.euraka.entity.User;

@RestController
@RequestMapping("/feign")
public class FeignController {

	private static final Logger log = LoggerFactory.getLogger(FeignController.class);
	

	@RequestMapping("/getUser")
	public User getUser(){
		User u = new User();
		u.setName("wsy");
		u.setSex("男");
		return u;
	}
	
	
}
