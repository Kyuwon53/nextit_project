package com.mayday.common.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FileUploadVO {
	private int fileNo;                     /*첨부파일 번호(PK)*/
	private int fileParentNo;               /*부모글의 PK */
	private String fileCategory;            /*상위글 분류(BOARD, FREE, QNA, PDS 등)*/
	private String fileName;                /*실제 저장된 파일명*/
	private String fileOriginalName;        /*사용자가 올린 원래 파일명*/
	private int fileSize;                   /*파일 사이즈*/
	private String fileFancySize;           /*팬시 사이즈*/
	private String fileContentType;         /*컨텐츠 타입*/
	private String filePath;                /*저장 경로(board/2020) */
	private int fileDownHit;                /*다운로드 횟수*/
	private String fileDelYn;               /*삭제여부(스케쥴에 의해서 파일삭제처리)*/
	private String fileRegDate;             /*등록일*/
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getFileParentNo() {
		return fileParentNo;
	}
	public void setFileParentNo(int fileParentNo) {
		this.fileParentNo = fileParentNo;
	}
	public String getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileFancySize() {
		return fileFancySize;
	}
	public void setFileFancySize(String fileFancySize) {
		this.fileFancySize = fileFancySize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getFileDownHit() {
		return fileDownHit;
	}
	public void setFileDownHit(int fileDownHit) {
		this.fileDownHit = fileDownHit;
	}
	public String getFileDelYn() {
		return fileDelYn;
	}
	public void setFileDelYn(String fileDelYn) {
		this.fileDelYn = fileDelYn;
	}
	public String getFileRegDate() {
		return fileRegDate;
	}
	public void setFileRegDate(String fileRegDate) {
		this.fileRegDate = fileRegDate;
	}
	
	
}
