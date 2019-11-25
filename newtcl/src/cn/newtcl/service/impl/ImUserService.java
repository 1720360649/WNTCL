package cn.newtcl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.newtcl.dao.UserMapper;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.User;
import cn.newtcl.service.UserService;

@Service
public class ImUserService implements UserService {
	
	private static Logger log = Logger.getLogger(ImUserService.class);
	
	@Resource
	private UserMapper userDao;
	
	@Override
	public NewReturn login(User user) {
		
		NewReturn re = new NewReturn();
		
		if(user.getPhone() == null){
			re.setCode("-1");
			re.setMessage("账号为空");
			return re;
		}else if(user.getPassword() == null || user.getPassword().equals("")){
			re.setCode("-2");
			re.setMessage("密码为空");
			return re;
		}
		
		User temp = userDao.login(user);
		if(temp.getPhone() == null || ("").equals(temp.getPhone())){
			re.setCode("0");
			re.setMessage("账号不存在");
			log.info(user.getId()+":账号或密码错误");
			return re;
		}else if(temp.getPassword().equals(getMD5(user.getPassword(),user.getPhone()))){
			re.setCode("1");
			re.setMessage("账号密码正确");
			re.setObj(temp);
			return re;
		}else{
			re.setCode("0");
			re.setMessage("账号或密码错误");
			log.info(user.getId()+":密码错误!");
			return re;
		}
		
	}

	@Override
	public NewReturn updateUser(User user) {
		NewReturn re = new NewReturn();
		
		if(user.getId() == null){
			re.setCode("-1");
			re.setMessage("此次更改未给出必要主键");
			return re;
		}
		
		int temp = userDao.updateUser(user);
		
		if(temp > 0){
			re.setCode("1");
			re.setMessage("用户信息更新成功");
		}else{
			re.setCode("0");
			re.setMessage("用户信息未更新");
		}
		
		return re;
	}

	@Override
	public NewReturn findUserById(Integer id) {
		NewReturn re = new NewReturn();
		
		return null;
	}

	@Override
	public NewReturn getMe(User user) {
		NewReturn re = new NewReturn();
		
		if(user.getId() == null){
			if(user.getQqOpenId() == null || user.getQqOpenId().equals("")){
				if(user.getAlipayOpenid() == null || user.getAlipayOpenid().equals("")){
					if(user.getWechatOpenid() == null || user.getWechatOpenid().equals("")){
						re.setCode("-1");
						re.setMessage("此次查询未给出条件");
						return re;
					}
				}
			}
		}
		
		User temp = new User();
		
		temp = userDao.getMe(user);
		
		if(temp == null){
			re.setCode("0");
			re.setMessage("未查询到相应数据!");
		}else if(temp.getId() == null || temp.getId().equals("")){
			re.setCode("0");
			re.setMessage("未查询到相应数据!");
		}else{
			re.setCode("1");
			re.setMessage("查询成功");
			re.setObj(temp);
		}
		
		return re;
	}

	@Override
	public NewReturn add(User user) {
		NewReturn re = new NewReturn();
		
		if(user.getId() == null)
			if(user.getQqOpenId() == null || user.getQqOpenId().equals(""))
				if(user.getAlipayOpenid() == null || user.getAlipayOpenid().equals(""))
					if(user.getWechatOpenid() == null || user.getWechatOpenid().equals(""))
						if(user.getPhone() == null || user.getPhone().equals("")){
							re.setCode("-1");
							re.setMessage("此次注册未给出必填项!");
							return re;
						}
		//注册用户密码加密
		if(user.getPhone() != null && !(user.getPhone().equals(""))){
			if(user.getPassword() == null || user.getPassword().equals("")){
				re.setCode("-1");
				re.setMessage("未给出密码");
				return re;
			}
			String temp = user.getPassword();
			user.setPassword(getMD5(temp, user.getPhone()));
		}
		
		user.setManagerId(0);
		int temp = userDao.add(user);
		
		if(temp > 0){
			re.setCode("1");
			re.setMessage("信息添加成功!");
		}else{
			re.setCode("0");
			re.setMessage("信息添加失败!");
		}
		
		return re;
	}

	@Override
	public NewReturn otherLogin(User user) {
		NewReturn re = new NewReturn();
		if(user == null){
			re.setCode("-1");
			re.setMessage("未传入参数");
			return re;
		}
		if(user.getQqOpenId() != null && !user.getQqOpenId().equals("") || user.getAlipayOpenid() != null && !user.getAlipayOpenid().equals("") || user.getWechatOpenid() != null && !user.getWechatOpenid().equals("")){
			re = this.getMe(user);
			if(re.getCode().equals("0")){
				int temp1 = userDao.add(user);
				if(temp1 > 0){
					re.setCode("1");
					re.setMessage("快捷登陆信息添加成功!");
					re.setObj(user);
				}else{
					re.setCode("0");
					re.setMessage("快捷登陆失败,请重新登陆");
				}
			}else{
				re.setCode("1");
				re.setMessage("快捷登陆成功");
			}
		}else{
			re.setCode("-1");
			re.setMessage("请先执行登录操作!");
		}
		
		return re;
	}

	@Override
	public NewReturn find(User user) {
		NewReturn re = new NewReturn();
		if(user == null || !(user instanceof User)){
			re.setCode("-1");
			re.setMessage("未给出正确参数!");
			return re;
		}
		if(user.getId() == null)
			if(user.getPhone() == null)
				if(user.getPassword() == null)
					if(user.getName() == null)
						if(user.getEmail() == null)
							if(user.getBalance() == null)
								if(user.getQqOpenId() == null)
									if(user.getAlipayOpenid() == null)
										if(user.getWechatOpenid() == null)
											if(user.getManagerId() == null)
												if(user.getJurisdiction() == null){
													re.setCode("-2");
													re.setMessage("未给出查询条件!");
													return re;
												}

		List<User> temp = userDao.find(user);
		if(temp.size() <= 0){
			re.setCode("0");
			re.setMessage("未查询到相关数据!");
		}else{
			re.setCode("1");
			re.setMessage("查询到相关数据!");
			re.setObj(temp);
		}
		return re;
	}

	@Override
	public NewReturn checkPhone(String phone) {
		NewReturn re = new NewReturn();
		User user = new User();
		user.setPhone(phone);
		
		re = find(user);
		
		if(("1").equals(re.getCode())){
			re.setCode("0");
			re.setMessage("该号码已被注册!");
		}else{
			re.setCode("1");
			re.setMessage("该号码可用!");
		}
		
		return re;
	}
	
	
	/**
	 * Spring MD5
	 */
	public static String getMD5(String str,String phone) {
		String base = str +"/"+phone;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		log.info(md5);
		return md5;
	}

}
