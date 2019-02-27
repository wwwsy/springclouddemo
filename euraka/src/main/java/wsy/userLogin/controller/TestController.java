package wsy.userLogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wsy.userLogin.controller.result.ResultAPI;

@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/test")
	public <T> ResultAPI<?> test(){
		return ResultAPI.error(211, "hello");
	}
}
