package Lec99Project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Lec99Project.model.CourseVO;
import Lec99Project.model.StudentVO;
import Lec99Project.model.SubjectVO;
import Lec99Project.model.CourseVO;

public class CourseDao {
	
	public boolean insertCourse(CourseVO course) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");

			sb.append("INSERT INTO course (               " ) ;
			sb.append("		    	course_no  , student_no , subject_no " );
			sb.append("		     , class_no , period , score  , c_date   ");
			sb.append("	) VALUES ( ?, ? , ? ");
			sb.append("	          , ?, ?, ? , ?  ) ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 4. 구문객체 실행 밑 결과 
			// 바인드변수 설정
			int idx = 1;
			pstmt.setInt(idx++, course.getCourseNo());
			pstmt.setString(idx++, course.getStudentNo());
			pstmt.setInt(idx++, course.getSubjectNo());
			pstmt.setString(idx++, course.getClassNo());
			pstmt.setInt(idx++, course.getPeriod());
			pstmt.setString(idx++, course.getScore());
			pstmt.setString(idx++, course.getcDate());
			
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
	
	public boolean updateCourse(CourseVO course) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle"); 
			sb.append("UPDATE course ");
			sb.append("	    SET course_no = ?  , subject_no = ?    , class_no = ?  ");
			sb.append("        , period = ?    , score = ?    , c_date =?         ");
			sb.append("      WHERE student_no = ?               ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setInt(idx++, course.getCourseNo());
			pstmt.setInt(idx++, course.getSubjectNo());
			pstmt.setString(idx++, course.getClassNo());
			pstmt.setInt(idx++, course.getPeriod());
			pstmt.setString(idx++, course.getScore());
			pstmt.setString(idx++, course.getcDate());
			pstmt.setString(idx++, course.getStudentNo());
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
	public List getCourse(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		List<CourseVO> list = new ArrayList<CourseVO>();
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT a.course_no   , a.student_no  ,b.student_name  , a.subject_no  ");
			sb.append("          ,c.subject_name   , a.class_no  , a.period  , a.score  ,c.grade          ");
			sb.append("          , TO_CHAR(a.c_date,'YYYY.MM.DD') as c_date             ");
			sb.append("         FROM course a, student b    , subject c             ");
			sb.append("    where a.student_no = b.student_no                   ");
			sb.append("    and a.subject_no = c.subject_no                   ");
			sb.append("    and a.student_no = ?                                ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			
			pstmt = conn.prepareStatement(sb.toString()); 
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CourseVO course = new CourseVO();
				// Table -> VO (POJO) 객체
				course.setCourseNo(rs.getInt("course_no"));
				course.setStudentNo(rs.getString("student_no"));
				course.setStudentName(rs.getString("student_name"));
				course.setSubjectNo(rs.getInt("subject_no"));
				course.setSubjectName(rs.getString("subject_name"));
				course.setClassNo(rs.getString("class_no"));
				course.setPeriod(rs.getInt("period"));
				course.setScore(rs.getString("score"));
				course.setGrade(rs.getInt("grade"));
				course.setcDate(rs.getString("c_date"));
				list.add(course);
			}
			return list;
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
	 * 학생 조회 
	 * <b>해당 학생이 존재하지 않는 경우 null 리턴</b>
	 * @param no
	 * @return
	 */
	public CourseVO getCourse1(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		CourseVO course = null;
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT a.course_no   , a.student_no  ,b.student_name  , a.subject_no  ");
			sb.append("          ,c.subject_name   , a.class_no  , a.period  , a.score  ,c.grade          ");
			sb.append("          , TO_CHAR(a.c_date,'YYYY.MM.DD') as c_date             ");
			sb.append("         FROM course a, student b    , subject c             ");
			sb.append("    where a.student_no = b.student_no                   ");
			sb.append("    and a.subject_no = c.subject_no                   ");
			sb.append("    and a.student_no = ?                                ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			
			pstmt = conn.prepareStatement(sb.toString()); 
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 course = new CourseVO();
				// Table -> VO (POJO) 객체
				course.setCourseNo(rs.getInt("course_no"));
				course.setStudentNo(rs.getString("student_no"));
				course.setStudentName(rs.getString("student_name"));
				course.setSubjectNo(rs.getInt("subject_no"));
				course.setSubjectName(rs.getString("subject_name"));
				course.setClassNo(rs.getString("class_no"));
				course.setPeriod(rs.getInt("period"));
				course.setScore(rs.getString("score"));
				course.setGrade(rs.getInt("grade"));
				course.setcDate(rs.getString("c_date"));
			}
			return course;
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
	public List getCourseList() {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		List<CourseVO> list = new ArrayList<CourseVO>();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT a.course_no   , a.student_no  ,b.student_name  , a.subject_no  ");
			sb.append("          ,c.subject_name   , a.class_no  , a.period  , a.score          ");
			sb.append("           ,c.grade ,  TO_CHAR(a.c_date,'YYYY.MM.DD') as c_date        ");
			sb.append("         FROM course a, student b    , subject c             ");
			sb.append("    where a.student_no = b.student_no                   ");
			sb.append("    and a.subject_no = c.subject_no                   ");
			sb.append("    ORDER BY course_no    ASC               ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString()); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CourseVO course = new CourseVO();
				
				// Table -> VO (POJO) 객체
				course.setCourseNo(rs.getInt("course_no"));
				course.setStudentNo(rs.getString("student_no"));
				course.setStudentName(rs.getString("student_name"));
				course.setSubjectNo(rs.getInt("subject_no"));
				course.setSubjectName(rs.getString("subject_name"));
				course.setClassNo(rs.getString("class_no"));
				course.setPeriod(rs.getInt("period"));
				course.setScore(rs.getString("score"));
				course.setGrade(rs.getInt("grade"));
				course.setcDate(rs.getString("c_date"));
				list.add(course);
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
