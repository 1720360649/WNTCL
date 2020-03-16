package cn.newtcl.service;

import java.util.List;

import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Shop;
import cn.newtcl.entity.User;

public interface UserService {

	NewReturn login(User user);
	
	NewReturn updateUser(User user);
	
	NewReturn findUserById(Integer id);
    
	NewReturn getMe(User user);
    
	NewReturn add(User user);
    
	NewReturn otherLogin(User user);
	
	NewReturn find(User user);
	
	NewReturn checkPhone(String phone);
	
	List<Shop> findAllShop();
}
