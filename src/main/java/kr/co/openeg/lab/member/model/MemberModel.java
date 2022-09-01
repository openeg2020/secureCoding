package kr.co.openeg.lab.member.model;

import org.springframework.stereotype.Component;

@Component
public class MemberModel {
	private String userId;
	private String userPw;
	private String userName;
	private String pinNo;
	private String joinDate;
	
	
	public MemberModel() {}
	
	public MemberModel(String userId, String userPw, String userName, String pinNo, String joinDate) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.pinNo = pinNo;
		this.joinDate = joinDate;
	}

	/**
	 * @return the pinNo
	 */
	public String getPinNo() {
		return pinNo;
	}
	/**
	 * @param pinNo the pinNo to set
	 */
	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}	
}
