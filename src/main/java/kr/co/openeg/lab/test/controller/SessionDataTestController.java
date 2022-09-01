package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.Customer;
import kr.co.openeg.lab.util.CustomerService;

@Controller
public class SessionDataTestController {
	
	// 세션정보 노출 
		@RequestMapping(value="/test/session_data_test",produces = "application/text; charset=utf8")
		@ResponseBody
		public String testSessionData(HttpServletRequest request, HttpSession session){
			StringBuffer buffer=new StringBuffer();	
			CustomerService cust=CustomerService.getInstance();	
			Customer c=new Customer(request.getParameter("name"),
		                                                  request.getParameter("phone"));
			
			//session.setAttribute("customer", cust.getCustomer());
			try {
				cust.setCustomer(c);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				buffer.append("요청 작업을 처리할 수 없습니다: 10001");
			}
			//Customer c=(Customer)session.getAttribute("customer");
			synchronized (this) {
				buffer.append(cust.displayCustomer());
			}
	        return buffer.toString();	
		}	
	
	

}
