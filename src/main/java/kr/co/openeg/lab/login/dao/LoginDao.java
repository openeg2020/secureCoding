package kr.co.openeg.lab.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.openeg.lab.login.model.LoginSessionModel;
import kr.co.openeg.lab.member.model.MemberModel;

@Repository
public class LoginDao {
	
	@Autowired
	private SqlSession sqlSession;

	private static final String mapper = "kr.co.openeg.lab.login.dao.LoginDao";
	
	public MemberModel selectUserId(String userId) {
		return (MemberModel) sqlSession.selectOne(mapper+".loginCheck1", userId);

	}	
	public MemberModel selectUserId(LoginSessionModel loginModel) {
		return (MemberModel) sqlSession.selectOne(mapper+".loginCheck2", loginModel);                			                      
	}	
}
