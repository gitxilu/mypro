package cn.qidian.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.qidian.bean.Page;
import cn.qidian.bean.Reply;
import cn.qidian.bean.Topic;
import cn.qidian.bean.Type;
import cn.qidian.bean.User;
import cn.qidian.exception.NameExistsException;
import cn.qidian.exception.NameOrPasswordErrorException;
import cn.qidian.exception.RegistForm;
import cn.qidian.service.BbsService;
import cn.qidian.service.PageService;

public class BbsServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
           doGet(request,response);
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		if("login".equals(method)){
			this.login(request, response);
		}else if("regist".equals(method)){
			this.regist(request, response);
		}else if("toLogin".equals(method)){
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}else if("toRegist".equals(method)){
			request.getRequestDispatcher("/jsp/regist.jsp").forward(request, response);
		}else if("exit".equals(method)){
			this.exit(request, response);
		}else if("back".equals(method)){
			request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);
		}else if("findAllType".equals(method)){
			this.findAllType(request, response);
		}else if("findAllTopicBytypeid".equals(method)){
			this.findAllTopicBytypeid(request, response);
		}else if("toAddTopic".equals(method)){
			request.getRequestDispatcher("/jsp/addTopic.jsp").forward(request, response);
		}else if("addTopic".equals(method)){
		    this.addTopic(request, response);
		}else if("lookReply".equals(method)){
			this.lookReply(request, response);
		}else if("backTotopicList".equals(method)){
			this.backTotopicList(request, response);
		}else if("toReply".equals(method)){
			request.getRequestDispatcher("/jsp/reply.jsp").forward(request, response);
		}else if("reply".equals(method)){
			this.reply(request, response);
		}

	}
	//主题回复
	private void reply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			Reply reply = new Reply();
			Enumeration<String> enums = request.getParameterNames();
			while(enums.hasMoreElements()){
				String elename = enums.nextElement();
				String elevalue = request.getParameter(elename);
				BeanUtils.setProperty(reply, elename, elevalue);
			}
			BbsService bbsService = new BbsService();
			bbsService.reply(reply);
//			response.sendRedirect(request.getContextPath()+"/jsp/topicList.jsp");
			request.getRequestDispatcher("/servlet/BbsServlet?method=lookReply").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            request.getRequestDispatcher("/servlet/BbsServlet?method=toReply").forward(request, response);
		}
	}
	//返回主题界面
	private void backTotopicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/servlet/BbsServlet?method=findAllTopicBytypeid").forward(request, response);
		
	}
	//根据主题编号查看对应所有回复
	private void lookReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PageService pageService = new PageService();
			Page page = new Page();
/*			String strtypeid = request.getParameter("typeid");
			System.out.println(strtypeid);*/
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
			request.getRequestDispatcher("/jsp/lookReply.jsp").forward(request, response);
			
			
			
			
			
/*			String strtopicid = request.getParameter("topicid");
			BbsService bbsService = new BbsService();
			System.out.println(strtopicid);
			List<Reply> replyList = bbsService.lookReply(Integer.parseInt(strtopicid));
			request.setAttribute("replyList", replyList);*/
			
//			request.getRequestDispatcher("/jsp/lookReply.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "查看回复失败");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}
	//发表主题
	private void addTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    try {
			Topic topic = new Topic();
			Enumeration<String> enums = request.getParameterNames();
			while(enums.hasMoreElements()){
				String elename = enums.nextElement();
				String elevalue = request.getParameter(elename);
				BeanUtils.setProperty(topic, elename, elevalue);
			}
			BbsService bbsService = new BbsService();
			bbsService.addTopic(topic);
			request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据对应编号查询所有主题
	private void findAllTopicBytypeid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		  try {
			  String strtypeid = request.getParameter("typeid");
			  String clientToken = request.getParameter("token");
			  String serverToken = (String) request.getSession().getAttribute("token");
			  BbsService bbsService = new BbsService();

			  if(clientToken !=null && serverToken != null && clientToken.equals(serverToken)){
				  bbsService.updateClickBytypeid(Integer.parseInt(strtypeid));
				  
				  request.getSession().removeAttribute("token");
			  }
			  List<Topic> topicList = bbsService.findAllTopicBytypeid(Integer.parseInt(strtypeid));
			  request.setAttribute("topicList", topicList);
			  request.getRequestDispatcher("/jsp/topicList.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "查询所有主题失败");
			request.getRequestDispatcher("/jsp/message.jsp");
		}
	}
	//查询所有版块
	private void findAllType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			BbsService bbsService = new BbsService();
			List<Type> typeList = bbsService.findAllType();
			List<Type> typeDesc = bbsService.findAllTypeByclick(typeList);
			request.setAttribute("typeList", typeList);
			request.setAttribute("typeDesc", typeDesc);
			request.getRequestDispatcher("/jsp/typeList.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "查询所有版块失败");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}
    
	//登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException{


			try {
				Enumeration<String> enums = request.getParameterNames();
				 User user = new User();
				 while(enums.hasMoreElements()){
					 String elename = enums.nextElement();
					 String[] elevalue = request.getParameterValues(elename);
					 BeanUtils.setProperty(user, elename, elevalue);
				 }
				 BbsService bbsService = new BbsService();
				 User u = bbsService.login(user);
				 if(u!=null){
					 request.getSession().setAttribute("user", user);
					 response.sendRedirect(request.getContextPath()+"/jsp/welcome.jsp");
//				 response.sendRedirect("http://localhost:8080/bbs/jsp/welcome.jsp");
				 }else{
					 throw new NameOrPasswordErrorException();
				 }
			} catch (NameOrPasswordErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	//注册
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			Enumeration<String> enums = request.getParameterNames();
             User user = new User();
			 while(enums.hasMoreElements()){
				 String elename = enums.nextElement();
				 String[] elevalue = request.getParameterValues(elename);
				 BeanUtils.setProperty(user, elename, elevalue);
			 }
			 RegistForm registForm = new RegistForm();
			 boolean flag = registForm.validate(user);
			 if(flag){
				 BbsService bbsService = new BbsService();
				 bbsService.regist(user);
				 request.setAttribute("message", "<font color='green' size='34'>注册成功</font>");
				 request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			 }else{
//				 request.setAttribute("registForm", registForm);
				 HttpSession session = request.getSession();
				 session.setAttribute("registForm", registForm);
				 request.getRequestDispatcher("/jsp/regist.jsp").forward(request, response);
			 }
		}catch(NameExistsException e){
			e.printStackTrace();
			request.setAttribute("message", "用户已存在");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "用户注册失败");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
		 
	}
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	HttpSession session = request.getSession();
    	session.invalidate();
    	response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
    }
    
	
}
