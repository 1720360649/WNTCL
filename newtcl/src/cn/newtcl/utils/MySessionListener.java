package cn.newtcl.utils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import cn.newtcl.entity.MySessionContext;

public class MySessionListener implements HttpSessionListener {
	
	 private MySessionContext myc = MySessionContext.getInstance();
	
	 public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
	        HttpSession session = httpSessionEvent.getSession();  
	        myc.addSession(session);  
	    }  
	  
	    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
	        HttpSession session = httpSessionEvent.getSession();  
	        myc.delSession(session);  
	    }

}
