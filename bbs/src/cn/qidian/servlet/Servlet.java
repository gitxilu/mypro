package cn.qidian.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.qidian.bean.Page;
import cn.qidian.service.PageService;

public class Servlet extends HttpServlet {
     

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    try {
			PageService pageService = new PageService();
			Page page = new Page();
			String strtopicid = request.getParameter("topicid");
			Integer topicid = 6;
			if(strtopicid!=null){
				topicid = Integer.parseInt(strtopicid);
			}
			String strpageNow = request.getParameter("pageNow");
			Integer pageNow = 1;
			if(strpageNow!=null){
				pageNow = Integer.parseInt(strpageNow);
			}
			if(pageNow==null){
				pageNow = 1;
			}
			page = pageService.getCurrentReply(pageNow, topicid);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/replyList.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		    try {
				PageService pageService = new PageService();
				Page page = new Page();
				String strtypeid = request.getParameter("typeid");
				Integer typeid = 1;
				if(strtypeid!=null){
					typeid = Integer.parseInt(strtypeid);
				}
				String strpageNow = request.getParameter("pageNow");
				Integer pageNow = 1;
				if(strpageNow!=null){
					pageNow = Integer.parseInt(strpageNow);
				}
				if(pageNow==null){
					pageNow = 1;
				}
				page = pageService.getCurrentTopic(pageNow,typeid);
				request.setAttribute("page", page);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
            
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          
		doGet(request,response);

	}

}
