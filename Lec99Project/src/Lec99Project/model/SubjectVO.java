package Lec99Project.model;

public class SubjectVO {
	
	private Integer subjectNo;
	private String subjectName;
	private Integer grade;
	
	public Integer getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(Integer subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "SubjectVO [subjectNo=" + subjectNo + ", subjectName=" + subjectName + ", grade=" + grade + "]";
	}
	
	
}
