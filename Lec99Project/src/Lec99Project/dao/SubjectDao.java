package Lec99Project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Lec99Project.model.SubjectVO;

public class SubjectDao {
	public boolean insertSubject(SubjectVO subject) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");

			sb.append("INSERT INTO subject (               " ) ;
			sb.append("		    	subject_no  , subject_name , grade " );
			sb.append("	) VALUES ( ?, ? , ? )");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 4. 구문객체 실행 밑 결과 
			// 바인드변수 설정
			int idx = 1;
			pstmt.setInt(idx++, subject.getSubjectNo());
			pstmt.setString(idx++, subject.getSubjectName());
			pstmt.setInt(idx++, subject.getGrade());
			
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
	
	public boolean updateSubject(SubjectVO subject) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle"); 
			sb.append("UPDATE subject ");
			sb.append("	    SET subject_name = ?  , grade = ?     ");
			sb.append("      WHERE subject_no = ?               ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, subject.getSubjectName());
			pstmt.setInt(idx++, subject.getGrade());
			pstmt.setInt(idx++, subject.getSubjectNo());
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
	 * 과목 조회 
	 * <b>해당 과목이 존재하지 않는 경우 null 리턴</b>
	 * @param no
	 * @return
	 */
	public SubjectVO getSubject(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		SubjectVO subject = null;
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT subject_no   , subject_name  , grade   ");
			sb.append("         FROM subject                    ");
			sb.append("    where subject_no = ?                  ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			
			pstmt = conn.prepareStatement(sb.toString()); 
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				subject = new SubjectVO();
				// Table -> VO (POJO) 객체
				subject.setSubjectNo(rs.getInt("subject_no"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setGrade(rs.getInt("grade"));
			}
			return subject;
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
	 * 전체 과목 목록 리턴 
	 * @return
	 */
	public List getSubjectList() {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		List<SubjectVO> list = new ArrayList<SubjectVO>();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.18:1521:XE", "java", "oracle");
			sb.append("	SELECT subject_no   , subject_name  , grade  ");
			sb.append("         FROM subject                    ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString()); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SubjectVO subject = new SubjectVO();
				// Table -> VO (POJO) 객체
				subject.setSubjectNo(rs.getInt("subject_no"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setGrade(rs.getInt("grade"));
				list.add(subject);
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
