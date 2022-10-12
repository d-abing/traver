package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/mschdList")
public class MschdListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MschdListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<ScheduleInfo> scheduleList = new ArrayList<ScheduleInfo>();
		// 내일정 목록을 저장하기 위한 ArrayList로 안에 저장될 데이터는 ScheduleInfo형 인스턴스만 허용

		String keyword = request.getParameter("keyword");	// 일정명 검색어 keyword
		String where = "";		// 검색어가 있을 경우 where절을 저장할 변수 
		if (keyword == null) { // 키워드검색어가 없으면
			keyword = "";
		} else if (!keyword.equals("")) {	// 검색어가 있을 경우
			URLEncoder.encode(keyword, "UTF-8"); // 쿼리스트링으로 주고 받는 검색어가 한글일 경우 IE에서 간혹 문제가 발생할 수 있으므로 유니코드로 변환시킴	
			where = " where si_name like '%" + keyword + "%' ";
		}
		
		MschdListSvc mschdListSvc = new MschdListSvc();
		scheduleList = mschdListSvc.getMschdList(where); // 목록화면에서 보여줄 일정 목록 ArrayList형으로 받아옴
		
        request.setAttribute("scheduleList", scheduleList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/mysche/mschd_list.jsp");
        dispatcher.forward(request, response);
	}
}
