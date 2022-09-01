package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.Role;
import kr.co.openeg.lab.util.TestUtil;

@Controller
public class EncapTestController {

	// 캡슐화 위배
	@RequestMapping(value = "/test/encap_test")
	@ResponseBody
	public String testEncapsulation(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		Role role = Role.getInstance();
		String data = request.getParameter("data");
		String type = request.getParameter("type");
		switch (TestUtil.getInt(type)) {
		case 0:
			buffer.append(role.adminLists());
			break;
		case 1:
			String[] list = role.getAdminRoles();
			list[0] = data;
			buffer.append(role.adminLists());
			break;
		case 2:
			String[] users = data.split(",");
			role.setAdminRoles(users);
			buffer.append("새로 설정된 관리자 목록: " + role.adminLists() + "<br/>");
			users[0] = "hacker";
			buffer.append("위조된 관리자 목록: " + role.adminLists());
			break;
		default:
		}
		return buffer.toString();
	}

}
