package kr.co.openeg.lab.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.openeg.lab.member.model.MemberModel;
import kr.co.openeg.lab.member.model.MemberSecurity;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String mapper = "kr.co.openeg.lab.member.dao.MemberDao";
	
	public void insertMember(MemberModel memberModel) {
		sqlSession.insert(mapper+".addMember", memberModel);
	}

	public MemberModel selectMember(String userId) {
		return (MemberModel) sqlSession.selectOne(mapper + ".findByUserId", userId);
	}

	public void deleteMember(MemberModel memberModel) {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper+".deleteMember", memberModel);
	}

	public void updateMember(MemberModel memberModel) {
		// TODO Auto-generated method stub
		sqlSession.update(mapper+".updateMember", memberModel);
	}
	
	public int  insertMemberSecurity(MemberSecurity mSecurity) {
		// TODO Auto-generated method stub
		return sqlSession.insert(mapper+".addMemberSecurity", mSecurity);
	}
	
	public MemberSecurity selectMemberSecurity(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper+".findMemberSecurity", userId);
	}
	
	public int  updateMemberSecurity(MemberSecurity mSecurity) {
		// TODO Auto-generated method stub
		return sqlSession.update(mapper+".updateMemberSecurity", mSecurity);
	}

}
