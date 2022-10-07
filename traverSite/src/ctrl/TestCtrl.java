package ctrl;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/test")
public class TestCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TestCtrl() { super();}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String piid =  request.getParameter("piid"); // ��ٱ��� index��ȣ
        String piname = request.getParameter("piname"); // ������ �ɼ�
        
        HttpSession session = request.getSession();
        ArrayList<PlaceInfo> addPlaceList = (ArrayList<PlaceInfo>)session.getAttribute("addPlaceList");
        PlaceInfo pi = new PlaceInfo();
        pi.setPi_id(piid);
        pi.setPi_name(piname);
        addPlaceList.add(pi); 
        session.setAttribute("scheduleList", addPlaceList);

        int result = addPlaceList.size();   

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
	}
}