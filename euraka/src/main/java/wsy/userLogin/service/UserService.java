package wsy.userLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wsy.userLogin.dao.UserDao;
import wsy.userLogin.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	public User queryByName(String userName){
		return userDao.queryByName(userName);
	}
	public void save(User user){
		userDao.save(user);
	}
	public void delete(User user){
		userDao.delete(user);
	}
}
