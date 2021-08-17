package com.mayday.member.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.valid.LaywerSignup1;
import com.mayday.common.valid.LaywerSignup2;
import com.mayday.common.valid.MemberSignup1;
import com.mayday.common.valid.MemberSignup2;
import com.mayday.common.valid.Modify;
import com.mayday.common.vo.MemberProfileVO;

@SuppressWarnings("serial")
public class MemberVO implements Serializable {

	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memId;              /* 아이디*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memPass;            /* 비밀번호*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memName;            /* 이름*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memBir;             /* 생년월일*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	@Size(max=15)
	private String memPhone;           /* 핸드폰*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memMail;            /* 이메일*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memAdd1;            /* 주소*/
	@NotBlank(groups = {LaywerSignup2.class,MemberSignup2.class})
	private String memAdd2;            /* 상세주소*/
	@NotBlank(groups = {LaywerSignup2.class, Modify.class})
	private String memCareer;          /* 이력*/
	private String memPosit;           /* 멤버 구분 */
	private String memCond;            /* 상태(Y : 활동, N : 탈퇴, H : 휴면,승인대기)*/
	@NotBlank(message = "체크에러", groups = {LaywerSignup1.class,MemberSignup1.class, Modify.class})
	private String agreeYn;           /*이용약관동의 		필수*/
	@NotBlank(message = "체크에러", groups = {LaywerSignup1.class,MemberSignup1.class, Modify.class})
	private String privacyYn;         /*개인정보동의 		필수*/
	private String eventYn;           /*이벤트알림수신동의 	선택*/
	private String endLogin;
	
	private String memRegDate;              /*등록날짜*/
	private String memEndDate;              /*최근 접속 날짜*/
	
	private MemberProfileVO memProfile;		/*프로필 사진 정보*/
	private String memState;                /*현재 활동상태*/
	
	
	public String getEndLogin() {
		return endLogin;
	}
	public void setEndLogin(String endLogin) {
		this.endLogin = endLogin;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}		
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	public String getMemCareer() {
		return memCareer;
	}
	public void setMemCareer(String memCareer) {
		this.memCareer = memCareer;
	}
	public String getMemPosit() {
		return memPosit;
	}
	public void setMemPosit(String memPosit) {
		this.memPosit = memPosit;
	}
	public String getMemCond() {
		return memCond;
	}
	public void setMemCond(String memCond) {
		this.memCond = memCond;
	}

	public String getEventYn() {
		return eventYn;
	}
	public void setEventYn(String eventYn) {
		this.eventYn = eventYn;
	}
	public String getAgreeYn() {
		return agreeYn;
	}
	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}
	public String getPrivacyYn() {
		return privacyYn;
	}
	public void setPrivacyYn(String privacyYn) {
		this.privacyYn = privacyYn;
	}
	public String getMemRegDate() {
		return memRegDate;
	}
	public void setMemRegDate(String memRegDate) {
		this.memRegDate = memRegDate;
	}
	public String getMemEndDate() {
		return memEndDate;
	}
	public void setMemEndDate(String memEndDate) {
		this.memEndDate = memEndDate;
	}
	public MemberProfileVO getMemProfile() {
		return memProfile;
	}
	public void setMemProfile(MemberProfileVO memProfile) {
		this.memProfile = memProfile;
	}
	public String getMemState() {
		return memState;
	}
	public void setMemState(String memState) {
		this.memState = memState;
	}
	
}
