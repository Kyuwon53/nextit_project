package Lec99Project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Lec99Project.model.StudentVO;

public class StudentDao {
	
	public boolean insertStudent(StudentVO student) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");

			sb.append("INSERT INTO student (               " ) ;
			sb.append("		    	student_no  , student_name , student_addr " );
			sb.append("		     , major , minor , birth ");
			sb.append("		     , semester , grade ");
			sb.append("	) VALUES ( ?, ? , ? ");
			sb.append("	          , ?, ?, ? ");
			sb.append("	          , ?, ? ) ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 4. 구문객체 실행 밑 결과 
			// 바인드변수 설정
			int idx = 1;
			pstmt.setString(idx++, student.getStudentNo());
			pstmt.setString(idx++, student.getStudentName());
			pstmt.setString(idx++, student.getStudentaddr());
			pstmt.setString(idx++, student.getMajor());
			pstmt.setString(idx++, student.getMinor());
			pstmt.setString(idx++, student.getBirth());
			if(student.getSemester() == null) {
				pstmt.setNull(idx++, java.sql.Types.INTEGER);
			} else {
				pstmt.setInt(idx++, student.getSemester());
			}
			if(student.getGrade() == null) {
				pstmt.setNull(idx++,  java.sql.Types.DOUBLE);
			}else {
				pstmt.setDouble(idx++, student.getGrade());
			}
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				return true;
			}
			return false;
		}catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
		return false;
	}
	
	public boolean updateStudent(StudentVO student) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle"); 
			sb.append("UPDATE student ");
			sb.append("	    SET student_addr = ?  , major = ? ");
			sb.append("           , minor = ?                   ");
			sb.append("      WHERE student_no = ?               ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, student.getStudentaddr());
			pstmt.setString(idx++, student.getMajor());
			pstmt.setString(idx++, student.getMinor());
			pstmt.setString(idx++, student.getStudentNo());
			return true;
		}catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
		return false;
	}
	
	/**
	 * 학생 조회 
	 * <b>해당 학생이 존재하지 않는 경우 null 리턴</b>
	 * @param no
	 * @return
	 */
	public StudentVO getStudent(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		StudentVO student = null;
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT student_no , student_name  ,student_addr ");
			sb.append("          ,major  ,minor                           ");
			sb.append("          ,TO_CHAR(birth, 'YYYY.MM.DD') as birth   ");
			sb.append("          ,semester  ,grade                        ");
			sb.append("     FROM student                                  ");
			sb.append("    WHERE student_no = ?                           ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			
			pstmt = conn.prepareStatement(sb.toString()); 
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				student = new StudentVO();
				// Table -> VO (POJO) 객체
				student.setStudentNo(rs.getString("student_no"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentaddr(rs.getString("student_addr"));
				student.setMajor(rs.getString("major"));
				student.setMinor(rs.getString("minor"));
				student.setBirth(rs.getString("birth"));
				student.setSemester(rs.getInt("semester"));
				student.setGrade(rs.getDouble("grade"));
			}
			return student;
		} catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
	}
	
	/**
	 * 전체 학생 목록 리턴 
	 * @return
	 */
	public List getStudentList() {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		List<StudentVO> list = new ArrayList<StudentVO>();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT student_no , student_name  ,student_addr ");
			sb.append("          ,major  ,minor ");
			sb.append("          ,TO_CHAR(birth, 'YYYY.MM.DD') as birth");
			sb.append("          ,semester  ,grade  ");
			sb.append("     FROM student  ");
			sb.append("    ORDER BY student_no ASC");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString()); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StudentVO student = new StudentVO();
				// Table -> VO (POJO) 객체
				student.setStudentNo(rs.getString("student_no"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentaddr(rs.getString("student_addr"));
				student.setMajor(rs.getString("major"));
				student.setMinor(rs.getString("minor"));
				student.setBirth(rs.getString("birth"));
				student.setSemester(rs.getInt("semester"));
				student.setGrade(rs.getDouble("grade"));
				list.add(student);
			}
			return list;

		}catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();;
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
		return Collections.EMPTY_LIST; //  null 보다는 Collections.EMPTY_LIST 
	} 
	
}
