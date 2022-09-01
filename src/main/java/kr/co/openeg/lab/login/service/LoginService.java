package kr.co.openeg.lab.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.openeg.lab.login.dao.LoginDao;
import kr.co.openeg.lab.login.model.LoginSessionModel;
import kr.co.openeg.lab.member.model.MemberModel;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao dao;
	
	public MemberModel checkUserId(String userId) {
		return dao.selectUserId(userId);		
	}	

	public MemberModel checkUserId(LoginSessionModel loginModel) {
		return dao.selectUserId(loginModel);
	}	
}
