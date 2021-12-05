/*==================================
   IMemberDAO.java
   - 사용자 정의 자료형 클래스
 ===================================*/

package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMemberDAO 
{
	// 회원 정보 추가 메소드 선언
	public int add(MemberDTO member) throws SQLException;
	
	// 전체 인원 수 확인 메소드 선언
	public int count() throws SQLException;
	
	// 회원 리스트 확인 메소드 선언
	public ArrayList<MemberDTO> list() throws SQLException;
	
	
}
