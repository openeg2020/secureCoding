package kr.co.openeg.lab.test.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandTestController {

	// Command 인젝션
	@PostMapping(value = "test/command_test", produces = "application/text; charset=utf8")
	public String testCommandInjection(HttpServletRequest request, HttpSession session) {
		StringBuffer buffer = new StringBuffer();
		String data = request.getParameter("data");

		if (data != null && data.equals("type")) {
			data = data + " " + request.getSession().getServletContext().getRealPath("/") + "file1.txt";
			System.out.println(data);
		}

		Process process;
		String osName = System.getProperty("os.name");
		String[] cmd;

		if (osName.toLowerCase().startsWith("window")) {
			cmd = new String[] { "cmd.exe", "/c", data };
			for (String s : cmd)
				System.out.print(s + " ");
		} else {
			cmd = new String[] { "/bin/sh", data };
		}

		try {
			process = Runtime.getRuntime().exec(cmd);
			InputStream in = process.getInputStream();
			Scanner s = new Scanner(in, "EUC-KR");
			while (s.hasNextLine() == true) {
				buffer.append(s.nextLine() + "<br/>");
			}
		} catch (IOException e) {
			buffer.append("실행오류발생");
			e.printStackTrace();
		}
		return buffer.toString();

	}

}
