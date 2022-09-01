package kr.co.openeg.lab.test.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.co.openeg.lab.util.TestUtil;

@Controller
public class EncodeTestController {
	
	// 인코딩 테스트 
		@RequestMapping(value="/test/encode_test",method = RequestMethod.POST,produces = "application/text; charset=utf8")
		@ResponseBody
		public String testEncoding(HttpServletRequest request) throws UnsupportedEncodingException {
			System.out.println("변경 한 인코딩 테스트 컨트롤러");
			StringBuffer buffer=new StringBuffer();
			String data=request.getParameter("data");
			String type=request.getParameter("type");
			switch ( TestUtil.getInt(type)  ) {
			case 0:
				Encoder encoder = Base64.getEncoder();
				byte[] targetBytes = data.getBytes();
				byte[] encodedBytes = encoder.encode(targetBytes);
				buffer.append("BASE64 인코딩 테스트: "+ new String(encodedBytes)); break;
			case 1:
				buffer.append("BASE64 디코딩 테스트: "); break;
			case 2:
				buffer.append("URL 인코딩 테스트: "); break;
			case 3:
				buffer.append("URL 디코딩 테스트: "); break;
			case 4:
				buffer.append("HTML 인코딩 테스트: "); break;
			case 5:
				buffer.append("HTML 디코딩 테스트: "); break;
		    default:
		    	buffer.append("잘못된 요청입니다.");
			}
			return buffer.toString();
		}
		
		
		// 인코딩 테스트 
//		@RequestMapping(value="/test/encode_test",method = RequestMethod.POST,produces = "application/text; charset=utf8")
//		@ResponseBody
//		public String testEncoding(HttpServletRequest request) throws UnsupportedEncodingException {
//			System.out.println("변경 한 인코딩 테스트 컨트롤러");
//			StringBuffer buffer=new StringBuffer();
//			String data=request.getParameter("data");
//			String type=request.getParameter("type");
//			switch ( TestUtil.getInt(type)  ) {
//			case 0:
//				Encoder encoder = Base64.getEncoder();
//				byte[] targetBytes = data.getBytes();
//				byte[] encodedBytes = encoder.encode(targetBytes);
//				buffer.append("BASE64 인코딩 테스트: "+ new String(encodedBytes)); break;
//			case 1:
//				Decoder decoder = Base64.getDecoder();
//				byte[] decodedBytes = decoder.decode(data);
//				String result = new String(decodedBytes);
//				buffer.append("BASE64 디코딩 테스트: "+result); break;
//			case 2:
//				String str = URLEncoder.encode(data, "UTF-8");
//				buffer.append("URL 인코딩 테스트: "+str); break;
//			case 3:
//				String result1 = URLDecoder.decode(data, "UTF-8");
//				buffer.append("URL 디코딩 테스트: "+result1); break;
//			case 4:
//				String result2 = StringEscapeUtils.escapeHtml4(data);
//				buffer.append("HTML 인코딩 테스트: "+result2); break;
//			case 5:
//				String result3 = StringEscapeUtils.unescapeHtml4(data);
//				buffer.append("HTML 디코딩 테스트: "+result3); break;
//		    default:
//		    	buffer.append("잘못된 요청입니다.");
//			}
//			return buffer.toString();
//		}

}
