package wsy.userLogin.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wsy.userLogin.entity.User;


@Repository
public interface UserDao extends CrudRepository<User, Long >{
	@Query("select a from User a where a.userName = ?1")
	public User queryByName(String userName);
}
