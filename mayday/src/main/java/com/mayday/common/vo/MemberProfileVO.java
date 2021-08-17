package com.mayday.common.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

public class MemberProfileVO {
	private int memProfileNo;               /*첨부파일 번호(PK)*/
	private String memId;                      /*사용자 id PK */
	private String profileName;             /*실제 저장된 파일명*/
	private String profileOriginalName;     /*사용자가 올린 원래 파일명*/
	private long profileSize;                /*파일 사이즈*/
	private String profileFancySize;        /*팬시 사이즈*/
	private String profileContentType;      /*컨텐츠 타입*/
	private String profilePath;             /*저장 경로(board/2020) */
	private String profileRegDate;          /*등록일*/
	
	private String profileSavePath;			/*저장경로*/
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public int getMemProfileNo() {
		return memProfileNo;
	}
	public void setMemProfileNo(int memProfileNo) {
		this.memProfileNo = memProfileNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileOriginalName() {
		return profileOriginalName;
	}
	public void setProfileOriginalName(String profileOriginalName) {
		this.profileOriginalName = profileOriginalName;
	}
	public long getProfileSize() {
		return profileSize;
	}
	public void setProfileSize(long profileSize) {
		this.profileSize = profileSize;
	}
	public String getProfileFancySize() {
		return profileFancySize;
	}
	public void setProfileFancySize(String profileFancySize) {
		this.profileFancySize = profileFancySize;
	}
	public String getProfileContentType() {
		return profileContentType;
	}
	public void setProfileContentType(String profileContentType) {
		this.profileContentType = profileContentType;
	}
	public String getProfilePath() {
		return profilePath;
	}
	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	public String getProfileRegDate() {
		return profileRegDate;
	}
	public void setProfileRegDate(String profileRegDate) {
		this.profileRegDate = profileRegDate;
	}

	public String getProfileSavePath() {
		return profileSavePath;
	}

	public void setProfileSavePath(String profileSavePath) {
		this.profileSavePath = profileSavePath;
	}
	
}
