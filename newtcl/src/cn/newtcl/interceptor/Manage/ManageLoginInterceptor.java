package cn.newtcl.interceptor.Manage;

import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
  User: 鏅ㄦⅵ鎰忓織
  Date: 2019/5/30
  Time: 19:46
  Notes:
*/
public class ManageLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        Object manager = request.getSession().getAttribute("manager");
        if (manager==null) {
            String header = request.getHeader("X-Requested-With");
            //ajax
            if (header!=null&&header.equals("XMLHttpRequest")) {
                JSONObject ret=new JSONObject();
                ret.put("type", "error");
                ret.put("msg", "异常,请联重试或系管理员!");
                response.getWriter().println(ret.toString());
                return false;
            }
            
            request.getSession().setAttribute("errorMsg","请先登录!");
            response.sendRedirect("/newtcl/manage/loginPage.do");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {

    }
}
