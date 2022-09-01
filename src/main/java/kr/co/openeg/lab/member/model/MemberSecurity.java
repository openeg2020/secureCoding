package kr.co.openeg.lab.member.model;

import org.springframework.stereotype.Component;

@Component
public class MemberSecurity {
	private String userId;
	private String salt;
	private String secKey;
	private String comment;
	
	public MemberSecurity() {}
	public MemberSecurity(String userId, String salt, String secKey, String comment) {
		super();
		this.userId = userId;
		this.salt = salt;
		this.secKey = secKey;
		this.comment = comment;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the secKey
	 */
	public String getSecKey() {
		return secKey;
	}
	/**
	 * @param secKey the secKey to set
	 */
	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}