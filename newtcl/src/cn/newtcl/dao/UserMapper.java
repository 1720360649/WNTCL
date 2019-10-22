package cn.newtcl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.newtcl.entity.User;

/*
  User: 晨梦意志
  Date: 2019/6/13
  Time: 12:42
  Notes:
*/
@Repository
public interface UserMapper {
	
	
	User login(User user);
	
	int updateUser(User user);
	
    User findUserById(Integer id);
    
    User getMe(User user);
    
    int add(User user);
    
	List<User> find(User user);
}
