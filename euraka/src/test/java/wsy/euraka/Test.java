package wsy.euraka;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsy.userLogin.entity.User;
import wsy.userLogin.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
	@Autowired
	private UserService userService;
	
	@org.junit.Test
	public void daoTest(){
		User user = userService.queryByName("wsy");
		System.out.println(user.getName());
		
		
		
	}
}
