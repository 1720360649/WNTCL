package cn.newtcl.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Shop;
import cn.newtcl.entity.User;
import cn.newtcl.service.impl.ImUserService;
import cn.newtcl.utils.photoUp;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private ImUserService imUserService;
    
    @Autowired
    private HttpSession session;
    
    private static Logger log = Logger.getLogger(UserController.class);
    
    @RequestMapping("goodsdisplay")
    public String goodsDisplay(){
        return "user/goodsDisplay";
    }
    
    @RequestMapping("/login")
    @ResponseBody
    public Information userLogin(String phone,String password,String code){
    	Information in = new Information();
    	if((("").equals(phone) || phone == null) || (("").equals(password) || password == null) || (("").equals(code) ||code == null)){
    		in.setCode("0");
    		in.setMessage("所给数据不全,或格式有误!");
    		return in;
    	}
    	String rcode = (String)session.getAttribute("rcodes");
    	if(rcode == null || rcode.equals("")){
    		in.setCode("-1");
    		in.setMessage("获取验证码失败!清联系管理员!");
    	}else if(!code.equals(rcode)){
    		in.setCode("-2");
    		in.setMessage("验证码错误!");
    	}else{
    		User user = new User();
    		user.setPhone(phone);
    		user.setPassword(password);
    		NewReturn re = imUserService.login(user);
    		if("1".equals(re.getCode())){
    			User u = (User)re.getObj();
    			session.setAttribute("wntcluser",u);
    			in.setCode("1");
    			in.setMessage("登陆成功");
    		}else{
    			in.setCode("0");
    			in.setMessage(re.getMessage());
    		}
    	}
    	return in;
    }
    
    @RequestMapping("/checkphone")
    @ResponseBody
    public Information checkPhone(String phone){
    	NewReturn re = new NewReturn();
    	Information in =new  Information();
    	
    	re = imUserService.checkPhone(phone);
    	if(("1").equals(re.getCode())){
    		in.setCode("1");
    		in.setMessage(re.getMessage());
    	}else{
    		in.setCode("0");
    		in.setMessage(re.getMessage());
    	}
    			
    	return in;
    }
    
    @RequestMapping("/reg")
    @ResponseBody
    public Information Reg(String avatar,String phone,String name,String password,String mccode,String regType){
    	Information in = new Information();
    	NewReturn re = new NewReturn();
    	photoUp up = new photoUp();
    	
    	if((avatar == null || (("").equals(avatar))) || (phone == null || (("").equals(phone))) || (password == null || (("").equals(password))) || (regType == null || (("").equals(regType)))){
    		in.setCode("-1");
    		in.setMessage("所给信息不全!");
    		return in;
    	}else{
    		if(regType.equals("mc")){
    			if(mccode == null || ("").equals("")){
    				in.setCode("-1");
    				in.setMessage("未填写邀请码");
    			}else if(!mccode.equals("9527")){	//邀请码比对
    				in.setCode("0");
    				in.setMessage("邀请码错误");
    			}
    			return in;
    		}
    		
    		//上传头像至服务器
			if(up.out(avatar, phone)){
				//信息写入数据库
				User user = new User();
   				user.setAvatar("http://wntcl.top/Avatars/"+phone+".jpg");
   				user.setPhone(phone);
   				user.setName(name);
   				user.setPassword(password);
   				if(regType.equals("mc"))
   					user.setJurisdiction("m");
   				else
   					user.setJurisdiction("u");
   				
    			re = imUserService.add(user);
    				
    			if(re.getCode().equals("1")){
    				in.setCode("1");
    				in.setMessage("注册成功!");
    				user.setPassword("");
    				session.setAttribute("wntcluser",user);
    			}else{
    				in.setCode("0");
    				in.setMessage("注册失败!请重试或联系管理员!");
    			}
			}else{
				in.setCode("0");
				in.setMessage("服务器遇到未知错误!请重试!");
			}
    	}
    return in;
    }
    
    @RequestMapping("/goview")
    public String goView(){

    	User user = (User)session.getAttribute("wntcluser");
		if(user == null || user.getJurisdiction() == null)
			return "jsps/tologin";
		else{
			return "jsps/toindex";
//			//页面跳转
//			if("s".equals(user.getJurisdiction())){
//				//写入managerID
//				if(user.getManagerId() != null)
//					session.setAttribute("managerid", user.getManagerId());
//				return "jsps/supporter";
//			}else if("m".equals(user.getJurisdiction())){
//				//写入managerID
//				if(user.getManagerId() != null)
//					session.setAttribute("managerid", user.getManagerId());
//				return "jsps/supporter";
//			}else if("w".equals(user.getJurisdiction())){
//				//写入managerID
//				if(user.getManagerId() != null)
//					session.setAttribute("managerid", user.getManagerId());
//				return "jsps/waiter";
//			}else if("c".equals(user.getJurisdiction())){
//				//写入managerID
//				if(user.getManagerId() != null)
//					session.setAttribute("managerid", user.getManagerId());
//				return "jsps/kitchen";
//			}else if("u".equals(user.getJurisdiction())){
//			
//				return "jsps/tologin";
//			}else{
//				return "jsps/tologin";
//			}
		}	
    }
    
    @RequestMapping("/getuser")
    @ResponseBody
    public User GetUser(){

    	if(session.getAttribute("wntcluser") == null || ((User)session.getAttribute("wntcluser")).getId() == null || ((User)session.getAttribute("wntcluser")).getId() < 1){
//    		//test
//    		User u = new User();
//    		u.setAvatar("https://wntcl.top/newtcl/img/managePortrait.png");
//    		u.setName("卧槽无情");
//    		u.setId(1);
//    		u.setEmail("1234567891@qq.com");
//    		return u;
    		return null;
    	}
    	
    	if(((User)session.getAttribute("wntcluser")).getId() != null && ((User)session.getAttribute("wntcluser")).getId() > 0){
    		
    		User user = (User)session.getAttribute("wntcluser");
    		//出于安全考虑,虽然已加密但仍对账号密码和支付密码进行清除
    		user.setPassword("0");
    		user.setPayPassword("0");
    		
    		return user;
    		
    	}else{
    		return null;
    	}
    }
    
    @RequestMapping("/edituser")
    @ResponseBody
    public Boolean EditUser(User user){
    	if(GetUser() == null)
    		return false;
    	else
    		user.setId(GetUser().getId());
  
    	NewReturn re = imUserService.updateUser(user);
    	
    	if("1".equals(re.getCode())){
			User temp = GetUser();
			if(user.getName() != null)
				temp.setName(user.getName());
			if(user.getEmail() != null)
				temp.setEmail(user.getEmail());
			if(user.getPhone() != null )
				temp.setPhone(user.getPhone());
			if(user.getAvatar() != null)
				temp.setAvatar(user.getAvatar());
			if(user.getAlipayOpenid()!= null)
				temp.setAlipayOpenid(user.getAlipayOpenid());
			if(user.getWechatOpenid() != null)
				temp.setWechatOpenid(user.getWechatOpenid());
			
    		return true;	
		}
    	else
    		return false;
    }
    
    @RequestMapping("/findallshop")
    @ResponseBody
    public List<Shop> FindAllShop(User user){
 
    	return imUserService.findAllShop();
    	
    }
    
}
