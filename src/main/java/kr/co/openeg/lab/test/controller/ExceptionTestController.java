package kr.co.openeg.lab.test.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.TestUtil;

@Controller
public class ExceptionTestController {

	// 예외처리
	@RequestMapping(value = "/test/exception_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testException(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer buffer = new StringBuffer();
		String data = request.getParameter("data");
		String type = request.getParameter("type");
		System.out.println("*****: " + data + "-----" + type);
		switch (Integer.parseInt(type)) {
		// 에러메시지를 통한 정보노출
		case 0:
			try {
				int i = 100 / TestUtil.getInt(data);
				buffer.append("처리결과: " + i);
			} catch (Exception e) {
				e.printStackTrace();
				buffer.append("에러발생: " + e.getMessage());
			}
			break;
		// 에러처리부재
		case 1:
			String level = "S";
			try {
				if (data.equals("admin")) {
					level = "S";
				} else {
					level = "G";
				}
			} catch (Exception e) {
			}

			if (level.equals("S")) {
				buffer.append("관리자 권한으로 작업을 수행합니다");
			} else {
				buffer.append("일반사용자 권한으로 작업을 수행합니다");
			}
			break;
		// 부적절한 예외처리
		case 2:
			char[] cbuf = new char[256];
			FileReader fr;
			FileWriter fw;
			try {
				fr = new FileReader(data);
				fw = new FileWriter(data + "_new");
				fw.write(new Date().getTime() + ":  " + fr.read(cbuf));
				fr.close();
				fw.close();
			} catch (Exception e) {
				buffer.append("요청하신 작업을 처리할 수 없습니다.");
			}
			break;
		default:
			buffer.append("요청오류");
			break;
		}

		return buffer.toString();
	}

}
