package cn.newtcl.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.MySessionContext;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.User;
import cn.newtcl.service.impl.ImUserService;

@Controller
@RequestMapping("otherlogin")
public class otherLogin {

	@Resource
	private ImUserService imUserService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	//日志	
	//private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/qqlogin")
	public @ResponseBody Information qqLogin(){
		Information in = new Information();
		NewReturn re = new NewReturn();
		User user = new User();
		
		String qid = (String) request.getParameter("qid");
		String qtoken = (String) request.getParameter("qtoken");
		String qname = (String) request.getParameter("qname");
		String qavatar = (String) request.getParameter("qavatar");
		
		user.setQqOpenId(qid);
		user.setAvatar(qavatar);
		user.setName(qname);
		user.setManagerId(0);
		user.setJurisdiction("u");
	
		re = imUserService.otherLogin(user);
	
		if(re.getCode().equals("1")){
			User u = (User)re.getObj();
			session.setAttribute("wntcluser",u);
			in.setCode("1");
			in.setMessage("登陆成功");
		}else{
			in.setCode("0");
			in.setMessage(re.getMessage());
		}
		return in;
	}
	
	@RequestMapping("/alipaylogin")
	public @ResponseBody Information alipayLogin(String authcode,String sessionid){
		Information in = new Information();
		NewReturn re = new NewReturn();
		User user = new User();
		
		String alipayOpenid = null;
		String alipayToken = null;
		String name = null;
		String avatar = null;
		
		//获取支付宝接口
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		AlipayClient alipayClient = subDishList.getAlipayClient();

		if(authcode == null || authcode.equals("")){
			in.setCode("0");
			in.setMessage("未传入参数");
			return in;
		}
		
	//用authentic_code 换取 token
		AlipaySystemOauthTokenRequest trequest = new AlipaySystemOauthTokenRequest();
		trequest.setCode(authcode);
		trequest.setGrantType("authorization_code");
		try {
		    AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(trequest);
		    alipayOpenid = oauthTokenResponse.getAlipayUserId();
		    alipayToken = oauthTokenResponse.getAccessToken();
		} catch (AlipayApiException e) {
		    //处理异常
		    e.printStackTrace();
		}
		
		if(alipayToken == null){
			in.setCode("0");
			in.setMessage("获取token失败");
			return in;
		}
		
	//用 token 换取 用户信息
		AlipayUserInfoShareRequest urequest = new AlipayUserInfoShareRequest();
		try {
		    AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(urequest, alipayToken);
		    if(userinfoShareResponse.getNickName() != null){
		    	name = userinfoShareResponse.getNickName();
		    }
		    if(userinfoShareResponse.getAvatar() != null){
		    	avatar = userinfoShareResponse.getAvatar();
		    }
		} catch (AlipayApiException e) {
		    //处理异常
		    e.printStackTrace();
		}
		
		user.setAlipayOpenid(alipayOpenid);
		user.setAvatar(avatar);
		user.setName(name);
		user.setManagerId(0);	
		user.setJurisdiction("u");
		
		re = imUserService.otherLogin(user);
		
		if(re.getCode().equals("1")){
			User u = (User)re.getObj();
			HttpSession session2 = MySessionContext.getInstance().getSession(sessionid);
			session.setAttribute("wntcluser",u);
			session2.setAttribute("wntcluser",u);
			in.setCode("1");
			in.setMessage("登陆成功");
		}else{
			in.setCode("0");
			in.setMessage(re.getMessage());
		}
		
		return in;
	}
	
}
