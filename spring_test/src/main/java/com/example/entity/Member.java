package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity

@Table(name="members")
public class Member implements Serializable {

	public Member() {

	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "member_id")
	private int memberId;

	@Column(name = "member_email")
	private String memberEmail;

	@Column(name = "member_password")
	private String memberPassword;

	@Column(name = "member_name")
	private String memberName;

	@Column(name = "member_photo")
	private byte[] memberPhoto;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public byte[] getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public Member(int memberId, String memberEmail, String memberPassword, String memberName, byte[] memberPhoto) {
		super();
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberPhoto = memberPhoto;
	}

}
