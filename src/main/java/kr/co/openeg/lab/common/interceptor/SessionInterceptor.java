package kr.co.openeg.lab.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;


public class SessionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request,
		                                  HttpServletResponse response, Object handler) throws Exception {
		// check variable
		Object userId = request.getSession().getAttribute("userId");
		//
		if(request.getRequestURI().contains("/test/init_db")) {
			 return true;
		}

		// pass through when access login.do, join.do
		
		if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/member/join")){
			if(userId != null){
				response.sendRedirect(request.getContextPath() + "/board/list");
				return true;
			} else {
				return true;
			}
		}
		//
		// where other pages		
		if(userId == null){
			response.sendRedirect(request.getContextPath() + "/login");
			HttpSession session=request.getSession();
			session.setAttribute("errCode", "4");
			return false;
		} else {
			return true;
		}
		//		
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}
