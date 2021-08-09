package Lec99Project.model;

public class CourseVO {
	
	private Integer  courseNo;
	private String  studentNo;
	private String  studentName;
	private Integer  subjectNo;
	private String  subjectName;
	private String  classNo;
	private Integer  period;
	private String  score;
	private Integer  grade;
	private String  cDate;
	
	
	public Integer getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(Integer courseNo) {
		this.courseNo = courseNo;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
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
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	@Override
	public String toString() {
		return "CourseVO [courseNo=" + courseNo + ", studentNo=" + studentNo + ", studentName=" + studentName
				+ ", subjectNo=" + subjectNo + ", subjectName=" + subjectName + ", classNo=" + classNo + ", period="
				+ period + ", score=" + score + ", grade=" + grade + ", cDate=" + cDate + "]";
	}
	
	
}
