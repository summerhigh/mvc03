/*====================================
  MemberInsertController.java
  - 사용자 정의 컨트롤러
  - 회원 데이터 추가 액션 처리 클래스
  - DAO객체에 대한 의존성 주입
    → setter 메소드 추가
=====================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 『implements Controller』  혹은 『extends AbstractController』
//-- 서블릿에서 HttpServlet 을 상속받은 객체 역할
public class MemberInsertController implements Controller
{
	// dao관련 속성 구성 → 인터페이스 형태로 (IoC /DI 적용하기 위해)
	private IMemberDAO dao;
	
	// setter 메소드 구성
	public void setDao(IMemberDAO dao)
	{
		this.dao = dao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 캐릭터 인코딩 설정 (이거 안 했음!!!)
		request.setCharacterEncoding("UTF-8");
		
		// 이전 페이지로부터 넘어온 데이터 수신
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		
		try
		{
			MemberDTO dto  = new MemberDTO();
			
			dto.setName(name);
			dto.setTelephone(telephone);
			
			dao.add(dto);
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		// sendRedirect()  
		
		// mav.setViewName("/WEB-INF/view/MemberList.jsp");
		// 목록을 가지고 만날 수 없다. 빈깡통으로 클라이언트를 만나게 되는 것이다
		// 뷰를 지정만 하고 아무것도 넘겨주지 않는 것이다.
		
		// 다시 컨트롤러를 안내해서 디스패처서블릿이  MemberListController로 불러주게 되고
		// 싹 다 가지고 뷰페이지를 구성하게 된다. 
		// (총 인원수 바뀌고, 목록 바뀐 채로 갱신해서 클라이언트를 다시 만난다.)
		mav.setViewName("redirect:memberlist.action");
		
		return mav;
		
		
	}

}
