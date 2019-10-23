package cn.newtcl.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.User;

@Controller
@RequestMapping("views")
public class UserView {
	
	@Autowired                                                                                                          
	private HttpSession session;
	
	@RequestMapping("/userlogin")
	public String userlogin(Integer id){
		
		Orders order;
		
		if (session.getAttribute("orders") == null) {
			order = new Orders();
		} else {
			order = (Orders) session.getAttribute("orders");
		}
		//设置桌号
		order.setTable(1);
		//回设
		session.setAttribute("orders", order);
		
		session.setAttribute("managerid", id);
		
		if(id == null)
			return "jsps/noPermission";
		
		return "user/showpage";
	}
	
	@RequestMapping("showpage")
    public String showpage(){
    	return "user/showpage";
    }

    @RequestMapping("payment")
    public String payment(){
    	if(loginCheck())
			return "jsps/tologin";
    	return "jsps/payment";
    }
    
	@RequestMapping("/checklogin")
	public @ResponseBody Information checklogin(){
		Information in = new Information();
		if(loginCheck()){
			in.setCode("0");
			in.setMessage("未登陆");
			in.setHtml(session.getId());
		}else{
			in.setCode("1");
			in.setMessage("已登陆");
		}
		return in;
	}
	
	protected Boolean loginCheck(){
		
		if(session.getAttribute("wntcluser") == null)
			return true;
		if(((User)session.getAttribute("wntcluser")).getId() == null)
			return true;
		
		return false;
	}
	
}
