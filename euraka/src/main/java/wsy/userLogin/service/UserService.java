package wsy.userLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import wsy.userLogin.dao.UserDao;
import wsy.userLogin.entity.User;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	public User queryByName(String userName) throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
		User user = userDao.queryByName(userName);
		return user;
	}
	public void save(User user){
		userDao.save(user);
	}
	public void delete(User user){
		userDao.delete(user);
	}
}
