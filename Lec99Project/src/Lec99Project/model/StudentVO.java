package Lec99Project.model;

public class StudentVO {

	private String studentNo;              /* 학번 */
	private String studentName;            /* 이름 */
	private String studentaddr;            /* 주소 */
	private String major;             /* 전공 */
	private String minor;              /* 부전공 */
	private String birth;            /* 생년월일 */
	private Integer semester;             /* 학기 */
	private Double grade;            /* 평점 */
	
	
	// getter/setter , toString	

	@Override
	public String toString() {
		return "StudentVO [studentNo=" + studentNo + ", studentName=" + studentName + ", studentaddr=" + studentaddr
				+ ", major=" + major + ", minor=" + minor + ", birth=" + birth + ", semester=" + semester + ", grade="
				+ grade + "]";
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


	public String getStudentaddr() {
		return studentaddr;
	}


	public void setStudentaddr(String studentaddr) {
		this.studentaddr = studentaddr;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getMinor() {
		return minor;
	}


	public void setMinor(String minor) {
		this.minor = minor;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public Integer getSemester() {
		return semester;
	}


	public void setSemester(Integer semester) {
		this.semester = semester;
	}


	public Double getGrade() {
		return grade;
	}


	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
}
