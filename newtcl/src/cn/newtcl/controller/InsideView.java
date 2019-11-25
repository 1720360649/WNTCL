package cn.newtcl.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.newtcl.entity.User;

@Controller
@RequestMapping("inside")
public class InsideView {

	@Autowired
	private HttpSession session;
	
	@RequestMapping("/cooklogin")
	public String cooklogin(){
		if(loginCheck())
			return "jsps/tologin";
		if(!("c").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
			if(!("m").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
				if(!("s").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
					return "jsps/noPermission";
		
		session.setAttribute("staff",((User)session.getAttribute("wntcluser")).getId());
		session.setAttribute("managerid", ((User)session.getAttribute("wntcluser")).getManagerId());
		
		return "jsps/kitchen";
	}
	

	@RequestMapping("/waiterlogin")
	public String waiterlogin(){
		
		if(loginCheck())
			return "jsps/tologin";
		
		if(!("w").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
			if(!("m").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
				if(!("s").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
					return "jsps/noPermission";
		
		session.setAttribute("managerid", ((User)session.getAttribute("wntcluser")).getManagerId());
		
		return "jsps/waiter";
	}

	@RequestMapping("/supporter")
	public String supporter(){
		
		if(loginCheck())
			return "jsps/tologin";
	
		if(!("m").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
			if(!("s").equals(((User)session.getAttribute("wntcluser")).getJurisdiction()))
				return "jsps/noPermission";
		
		session.setAttribute("managerid", ((User)session.getAttribute("wntcluser")).getId());
		
		return "jsps/supporter";
	}

	
protected Boolean loginCheck(){
		
		if(session.getAttribute("wntcluser") == null)
			return true;
		if(((User)session.getAttribute("wntcluser")).getId() == null)
			return true;
		
		return false;
	}

}
