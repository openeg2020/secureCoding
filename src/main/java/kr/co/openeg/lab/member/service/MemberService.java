package kr.co.openeg.lab.member.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.openeg.lab.member.dao.MemberDao;
import kr.co.openeg.lab.member.model.MemberModel;
import kr.co.openeg.lab.member.model.MemberSecurity;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;

	public void deleteMember(MemberModel memberModel) {
		dao.deleteMember(memberModel);
	}

	public boolean modifyMember(MemberModel memberModel) {
		try {
			dao.updateMember(memberModel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int addMember(MemberModel memberModel) {
		if (dao.selectMember(memberModel.getUserId()) != null) {
			return 1;
		} else {
			try {
				//memberModel.setUserPw(bCryptPasswordEncoder.encode(memberModel.getUserPw()).toString());
				memberModel.setUserPw(memberModel.getUserPw());
				memberModel.setUserId(memberModel.getUserId());
				memberModel.setUserName(memberModel.getUserName());
				memberModel.setPinNo(memberModel.getPinNo());
				dao.insertMember(memberModel);
				return 3;
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		}
	}

	public MemberModel findMember(String userId) {
		return dao.selectMember(userId);
	}
		
	public int updateMemberSecurity(MemberSecurity mSecurity) {
		return dao.updateMemberSecurity(mSecurity);
	}
	
	public MemberSecurity findMemberSecurity(String userId) {
		return dao.selectMemberSecurity(userId);
	}
	
	public int addMemberSecurity(MemberSecurity mSecurity) {
		return dao.insertMemberSecurity(mSecurity);
	}

	public String makePin() {
		Random rand = new Random();
		String[] rValue = new String[13];
		rValue[6] = "-";
		for (int i = 0; i < 12; i++) {
			if (i < 6) {
				rValue[i] = Integer.toString(rand.nextInt(8 + 1) + 1);
			}
			if (i >= 6) {
				rValue[i + 1] = Integer.toString(rand.nextInt(8 + 1) + 1);
			}
			// 1 <= iValue < 10
		}
		String randomPin = String.join("", rValue);

		return randomPin;
	}

}
