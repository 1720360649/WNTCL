package cn.newtcl.dao;

import java.util.List;
/**
 * 2019/12/01 Over 
 * 王泽林
 */
import org.springframework.stereotype.Repository;

import cn.newtcl.entity.User;
@Repository
public interface UserMapper {
	
	
	User login(User user);
	
	int updateUser(User user);
	
    User findUserById(Integer id);
    
    User getMe(User user);
    
    int add(User user);
    
	List<User> find(User user);
}
