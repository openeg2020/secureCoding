package kr.co.openeg.lab.test.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegTestController {

	// 정규식 테스트
//	@RequestMapping(value = "/test/reg_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
//	@ResponseBody
//	public String testRegEx(HttpServletRequest request) {
//		StringBuffer buffer = new StringBuffer();
//		String data = request.getParameter("data");
////		String patternString = "(?=.*[0-9]+)(?=.*[a-zA-Z]+)(?=.*[~!@#$%^&*()_+]+).{8,15}";
//		String patternString = "<script\\s*>.*</script>";
//		if (data != null) {
//			Pattern p = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
//			Matcher m = p.matcher(data);
//			if (m.find()) {
//				buffer.append("인코딩 적용된 입력값: " + StringEscapeUtils.escapeHtml4(data));
//			} else {
//				buffer.append("입력값 확인: " + data);
//			}
//		} else {
//			buffer.append("입력값 오류");
//		}
//		return buffer.toString();
//	}
	

	@RequestMapping(value = "/test/reg_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testRegEx(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		String data = request.getParameter("data");
		String patternString = "(?=.*[0-9]+)(?=.*[A-Za-z]+)(?=.*[~!@#$%^&*()_+]+).{8,15}";
		if (data != null) {
			Pattern p = Pattern.compile(patternString);
			Matcher m = p.matcher(data);
			if (m.matches()) {
				buffer.append("사용가능한 패스워드:" + data);
			} else {
				buffer.append("사용할 수 없는 패스워드:" + data);
			}
		} else {
			buffer.append("입력값 오류");
		}
		return buffer.toString();
	}

}
